import Tests.TestClassFailed;
import Tests.TestClassPass;

public class Main {
    public static void main(String[] args) {
        System.out.println("run classes");
        Tester.run(new Class[]{TestClassPass.class});
        System.out.println();

        System.out.println("run classes");
        Tester.run(new Class[]{TestClassFailed.class});
        System.out.println();

        System.out.println("run package");
        Tester.run("Test");
            }
}
/*
Запускать вызовом статического метода с
1. именем класса с тестами,
2. именем package в котором надо найти и запустить тесты
 */