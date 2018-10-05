import Tests.TestClass;

public class Main {
    public static void main(String[] args) {
        System.out.println("run classes");
        Tester.run(new Class[]{TestClass.class});
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