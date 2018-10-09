package Tests;

import annotations.After;
import annotations.Assert;
import annotations.Before;
import annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestClassFailed {
    List<Integer> list;

    @Before
    public void beforeTest()
    {
        System.out.println("New Test start");
        list = new ArrayList<Integer>();
        list.add(0);
    }

    @Test
    public void failedTest()
    {
        System.out.println("Fail test start");
        Assert.assertEquals(true,false);
    }

    @After
    public void afterTest()
    {
        System.out.println("Test is over");
        list.clear();
    }

}
