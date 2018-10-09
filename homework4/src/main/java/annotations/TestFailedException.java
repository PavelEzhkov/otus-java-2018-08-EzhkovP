package annotations;

public class TestFailedException extends RuntimeException {
    public TestFailedException(String s) {
        super(s);
    }
}
