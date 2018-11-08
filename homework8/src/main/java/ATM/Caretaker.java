package ATM;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private final List<ATMMemento> ATMsMemento = new ArrayList<ATMMemento>();

    public void add(ATMMemento atmMemento) {
        ATMsMemento.add(atmMemento);
    }

    public ATMMemento get(int index) {
        return ATMsMemento.get(index);
    }

}
