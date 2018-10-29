public class Main {
    public static void main(String[] args) {

        ATMImplementation myAtm = new ATMImplementation();
        System.out.println(myAtm.getLimit());
        for (int i = 0; i < 7; i++) {
            myAtm.loadCash(100);
        }
        for (int i = 0; i < 3; i++) {
            myAtm.loadCash(500);
        }

        System.out.println(myAtm.getLimit());

        myAtm.getCash(1_000_000);

        myAtm.getCash(1_110);

        myAtm.getCash(7600);

        System.out.println(myAtm.getLimit());



    }
}
