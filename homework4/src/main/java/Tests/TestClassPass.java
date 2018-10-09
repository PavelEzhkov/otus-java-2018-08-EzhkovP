package Tests;

import annotations.After;
import annotations.Assert;
import annotations.Before;
import annotations.Test;



import java.util.ArrayList;
import java.util.List;

public class TestClassPass {
List<Integer> list;

@Before
public void beforeTest()
{
    System.out.println("New Test start");
    list = new ArrayList<Integer>();
    list.add(0);
}
@Test
public void testAdd()
{
    System.out.println("Add test start");
    Integer sizeBefore = list.size();
    list.add(1);
    Assert.assertEquals(sizeBefore+1,list.size());
}

@After
public void afterTest()
{
    System.out.println("Test is over");
    list.clear();
}

}
