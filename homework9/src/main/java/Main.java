import examples.ArrayEx;
import examples.Base;
import examples.CollectionEx;

public class Main {
    public static void main(String[] args) {
        System.out.println("Base examples : " + MyJsonWriter.toJsonString(new Base()));
        System.out.println("Array examples : " + MyJsonWriter.toJsonString(new ArrayEx()));
        System.out.println("Collection examples : " + MyJsonWriter.toJsonString(new CollectionEx()));

    }
}
