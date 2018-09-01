import com.google.common.collect.*;


public class Main {
    public static void main(String[] args) {
        RangeMap<Integer,String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.open(0,16),"Выраженный дефицит массы тела");
        rangeMap.put(Range.closed(16,18),"Недостаточная (дефицит) масса тела");
        rangeMap.put(Range.closed(19,24),"Норма");
        rangeMap.put(Range.closed(25,29),"Избыточная масса тела (предожирение)");
        rangeMap.put(Range.closed(30,34),"Ожирение");
        rangeMap.put(Range.closed(35,40),"Ожирение резкое");
        rangeMap.put(Range.closed(40,99),"Очень резкое ожирение");

        System.out.println(rangeMap.get(PersonBodeMassIndex(1.8d,85)));

    }
    private static int PersonBodeMassIndex (double Height, int Weight){
        double PBMI = Weight/(Height*Height);
        int PersonBodeMassIndex = (int) PBMI;
        return PersonBodeMassIndex;
    }
}
