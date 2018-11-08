import ATM.*;
import ATMDepartment.ATMDepartment;

public class Main {
    public static void main(String[] args) {
        ATMDepartment atmDepartment0 = new ATMDepartment();
        atmDepartment0.returnLimits();
        System.out.println("----------------------------------------");
        ATM atm1 = new ATMImplementation(100, 100, 100, 100);
        ATM atm2 = new ATMImplementation(200, 200, 200, 200);
        ATM atm3 = new ATMImplementation(300, 300, 300, 300);
        ATMDepartment atmDepartment = new ATMDepartment();
        atmDepartment.createNewATM("ATM1", atm1);
        atmDepartment.createNewATM("ATM2", atm2);
        atmDepartment.createNewATM("ATM3", atm3);
        atmDepartment.returnLimits();
        System.out.println("----------------------------------------");
        atm1.getCash(55000);
        atm1.loadCash(1000);
        atmDepartment.returnLimits();
        System.out.println("----------------------------------------");
        atmDepartment.setDefaultState();
        atmDepartment.returnLimits();

    }
}
