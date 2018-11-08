package ATM;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;


public class ATMImplementation implements ATM {
    private TreeMap<Integer, Integer> banknotesInATM = new TreeMap<>(Collections.<Integer>reverseOrder());

    public ATMImplementation(int amount100, int amount500, int amount1000, int amount5000) {
        setDefaultBanknotesInATM(amount100, amount500, amount1000, amount5000);
    }

    private int calculateLimit() {
        int result = 0;
        for (Map.Entry<Integer, Integer> map : banknotesInATM.entrySet()) {
            result += map.getKey() * map.getValue();
        }
        return result;
    }

    private void setDefaultBanknotesInATM(int amount100, int amount500, int amount1000, int amount5000) {
        this.banknotesInATM.put(100, amount100);
        this.banknotesInATM.put(500, amount500);
        this.banknotesInATM.put(1000, amount1000);
        this.banknotesInATM.put(5000, amount5000);
    }

    public int getLimit() {
        return calculateLimit();
    }

    public void getCash(int value) {
        if (value > getLimit())
            System.out.println("Requested value:" + value + " more than ATM limit:" + getLimit());
        else if (value % banknotesInATM.lastKey() != 0)
            System.out.println("It is not possible to issue the specified amount, please enter the amount multiple " + banknotesInATM.lastKey());
        else {
            System.out.print("Issued " + value + " :");
            for (Map.Entry<Integer, Integer> map : banknotesInATM.entrySet()) {
                int amount = value / map.getKey();
                banknotesInATM.put(map.getKey(), map.getValue() - amount);
                System.out.print(amount + "x" + map.getKey() + " ");
                value -= amount * map.getKey();
            }
            System.out.println();
        }
    }

    public void loadCash(int banknote) {
        for (Map.Entry<Integer, Integer> map : banknotesInATM.entrySet()) {
            if (map.getKey() == banknote)
                banknotesInATM.put(map.getKey(), map.getValue() + 1);
        }
    }

    public ATMMemento save() {
        return new ATMMemento(this.banknotesInATM);
    }

    public void restore(ATMMemento atmMemento) {
        this.banknotesInATM = atmMemento.getATMMemento();
    }

}
