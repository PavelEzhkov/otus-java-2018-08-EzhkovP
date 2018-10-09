package annotations;

public class Assert {
    public static void assertEquals(Object sample, Object value)
    {if (!sample.equals(value))
        throw new TestFailedException("Sample "+ sample + " is not equals value " + value);}
}
