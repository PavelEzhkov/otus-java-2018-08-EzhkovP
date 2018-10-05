package annotations;

public class Assert {
    public static void assertEquals(Object sample, Object value)
    {if (!sample.equals(value))
        System.out.println("Sample "+ sample + " is not equals value " + value);}
}
