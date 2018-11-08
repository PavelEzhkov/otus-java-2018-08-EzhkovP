package ATM;

public interface ATM {
    int getLimit();

    void getCash(int value);

    void loadCash(int banknote);

    ATMMemento save();

    void restore(ATMMemento atmMemento);
}
