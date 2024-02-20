package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
import java.time.format.DateTimeFormatter;

@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    private Task generateTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
    }

    // BEGIN
    @Test
    public void testShow() throws Exception {
        var task = generateTask();
        taskRepository.save(task);

        var result = mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).and(
                response -> response.node("id").isEqualTo(task.getId()),
                response -> response.node("title").isEqualTo(task.getTitle()),
                response -> response.node("description").isEqualTo(task.getDescription()),
                response -> response.node("createdAt").isEqualTo(task.getCreatedAt().format(DateTimeFormatter.ISO_DATE)),
                response -> response.node("updatedAt").isEqualTo(task.getUpdatedAt().format(DateTimeFormatter.ISO_DATE))
        );
    }

    @Test
    public void testCreate() throws Exception {
        var task = generateTask();

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(task))
                )
                .andExpect(status().isCreated());

        var taskFromDb = taskRepository.findById(1L);
        assertThat(taskFromDb).isNotEmpty();
    }

    @Test
    public void testUpdate() throws Exception {
        var task = generateTask();
        taskRepository.save(task);

        var data = new HashMap<>();
        data.put("title", "test");

        mockMvc.perform(put("/tasks/" + task.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(data))
                )
                .andExpect(status().isOk());

        var taskFromDb = taskRepository.findById(task.getId()).get();
        assertThat(taskFromDb.getTitle()).isEqualTo("test");
    }

    @Test
    public void testDelete() throws Exception {
        var task = generateTask();
        taskRepository.save(task);

        mockMvc.perform(delete("/tasks/" + task.getId()))
                .andExpect(status().isOk());

        var taskFromDb = taskRepository.findById(task.getId());
        assertThat(taskFromDb).isEmpty();
    }
    // END
}
