
public class Main {
    public static void main(String[] args) {
        CacheEngine<Integer, String> cache = new CacheEngine<>();
        for (int i = 0; i < 500_000; i++) {
            cache.put(i, "String" + i);
            System.out.println(i);
        }

        for (int i = 0; i < 50_000; i++) {
           String element = cache.get(i);
            System.out.println("String for " + i + ": " + element);
            }
    }
}
