package exercise;

// BEGIN
public class ReversedSequence implements CharSequence{
    private final String reversedSequence;

    public ReversedSequence(String sequence) {
        this.reversedSequence = new StringBuilder(sequence).reverse().toString();
    }

    @Override
    public int length() {
        return reversedSequence.length();
    }

    @Override
    public char charAt(int i) {
        return reversedSequence.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return reversedSequence.subSequence(i, i1);
    }

    public String toString() {
        return reversedSequence;
    }
}
// END
