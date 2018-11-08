package ATMDepartment;

import ATM.*;

import java.util.*;

public class ATMDepartment {
    private Map<String, ATM> ATMs = new TreeMap<>();
    private Caretaker caretaker = new Caretaker();


    public void createNewATM(String name, ATM atm) {
        ATMs.put(name, atm);
        caretaker.add(atm.save());
    }

    public void returnLimits() {
        if (ATMs.isEmpty()) {
            System.out.println("ATMs do not exist!");
            return;
        }
        int limit = 0;
        for (Map.Entry<String, ATM> map : ATMs.entrySet()) {
            limit += map.getValue().getLimit();
            System.out.println("ATM: " + map.getKey() + " limit: " + map.getValue().getLimit());
        }
        System.out.println("Total limit: " + limit);

    }

    public void setDefaultState() {
        int i = 0;
        for (Map.Entry<String, ATM> map : ATMs.entrySet()) {
            // int i =0;
            map.getValue().restore(caretaker.get(i++));
        }
    }
}
