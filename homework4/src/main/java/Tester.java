import annotations.Before;
import annotations.TestFailedException;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class Tester {
    static Class<?> before;
    static Class<?> test;
    static Class<?> after;
    static Map<Method, String> resultTest;
    static boolean testFailed;

    static {
        try {
            before = Class.forName("annotations.Before");
            test = Class.forName("annotations.Test");
            after = Class.forName("annotations.After");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void run(Class[] classes) {
        Method beforeMethod;
        List<Method> testMethods;
        Method afterMethod;

        for (Class<?> testClass : classes) {
            beforeMethod = null;
            testMethods = new ArrayList<>();
            afterMethod = null;
            resultTest = new HashMap<>();
            testFailed = false;

            for (Method method : testClass.getMethods()) {
                if (method.getDeclaringClass() == testClass) {
                    if (method.getDeclaredAnnotations().length == 0)
                        continue;
                    Class<?> getClass = method.getDeclaredAnnotations()[0].annotationType();
                    if (getClass == before)
                        beforeMethod = method;
                    else if (getClass == test)
                        testMethods.add(method);
                    else if (getClass == after)
                        afterMethod = method;

                }
            }
            Object testObject = ReflectionHelper.instantiate(testClass);
            if (beforeMethod != null) {
                try {
                    ReflectionHelper.callMethod(testObject, beforeMethod.getName());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new TestFailedException(e.getMessage());
                }
            }
            for (Method testMethod : testMethods) {
                try {
                    ReflectionHelper.callMethod(testObject, testMethod.getName());
                    resultTest.put(testMethod, "Passed");
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    if (e.getCause().getClass() == TestFailedException.class){
                        resultTest.put(testMethod, e.getCause().getMessage());
                    } else
                    resultTest.put(testMethod, e.getMessage());
                    testFailed = true;
                }
            }

            if (resultTest.isEmpty())
                continue;

            if (afterMethod != null) {
                try {
                    ReflectionHelper.callMethod(testObject, afterMethod.getName());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new TestFailedException(e.getMessage());
                }
            }

            if (testFailed)
                System.out.println("Test: " + testClass.getName() + " failed");
            else
                System.out.println("Test: " + testClass.getName() + " passed");
            System.out.println("Results:");
            resultTest.entrySet().forEach(System.out :: println);
        }
    }


    public static void run(String packageName){
        Set<Class> classes = new HashSet<>();
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()){
                if (info.getName().startsWith(packageName))
                    classes.add(info.load());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        run(classes.toArray(new Class[classes.size()]));
    }
}
