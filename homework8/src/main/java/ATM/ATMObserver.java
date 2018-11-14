package ATM;

public interface ATMObserver {
    int getLimit();

    void setDefault(int index);

    void getCash(int value);

    void loadCash(int banknote);

    ATMMemento save();

    void restore(ATMMemento atmMemento);
}
