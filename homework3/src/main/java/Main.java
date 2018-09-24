import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Test new ArrayList realization!");
        List<String> list = new ArrayList<>();
        list.add("Name2");
        list.add("Name3");
        list.add("Name1");
        list.add("Name4");
        System.out.println(list.get(2));
        System.out.println(list.size());
        System.out.println(list.hashCode());
        System.out.println(list.contains("Name3"));
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list2.add("");
        }
        Collections.copy(list2,list);
        list2.forEach(System.out::println);
        System.out.println();
        Collections.addAll(list,"Name5","Name6");
        list.forEach(System.out::println);
        System.out.println();
        Collections.sort(list);
        list.forEach(System.out::println);
        }
}
