package ATM;

import java.util.TreeMap;

public class ATMMemento {
    private final TreeMap<Integer, Integer> banknotesInATM;

    public ATMMemento(TreeMap<Integer, Integer> banknotesInATM) {
        this.banknotesInATM = new TreeMap<>(banknotesInATM);
    }

    public TreeMap<Integer, Integer> getATMMemento() {
        return this.banknotesInATM;
    }
}
