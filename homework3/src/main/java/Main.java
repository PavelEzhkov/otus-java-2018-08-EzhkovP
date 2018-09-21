import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Test new ArrayList realization!");
        List<String> list = new ArrayList<>();
        list.add("Name1");
        list.add("Name2");
        list.add("Name3");
        list.add("Name4");
        System.out.println(list.get(2));
        System.out.println(list.size());
        System.out.println(list.hashCode());
        System.out.println(list.contains("Name3"));
        }
}
