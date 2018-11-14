package ATMDepartment;

import ATM.*;

import java.util.*;

public class ATMDepartment {
    private Map<String, ATMObserver> ATMs = new TreeMap<>();


    public void createNewATM(String name, ATMObserver atm) {
        ATMs.put(name, atm);
    }

    public void returnLimits() {
        if (ATMs.isEmpty()) {
            System.out.println("ATMs do not exist!");
            return;
        }
        int limit=0;
        for (Map.Entry<String, ATMObserver> map : ATMs.entrySet()) {
            limit += map.getValue().getLimit();
            System.out.println("ATM: " + map.getKey() + " limit: " + map.getValue().getLimit());
        }

        System.out.println("Total limit: " + limit);

    }

    public void setDefaultState() {
         for (Map.Entry<String, ATMObserver> map : ATMs.entrySet()) {
            map.getValue().setDefault(map.getKey().indexOf(map.getKey()));
        }
    }
}
