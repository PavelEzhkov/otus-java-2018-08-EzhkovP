import dataSet.AddressDataSet;
import dataSet.PhoneDataSet;
import dbService.DBService;
import dbService.DBServiceHibernateImpl;
import dbService.DBServiceImpl;
import dataSet.UserDataSet;
import org.h2.tools.Server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {
        //oldMain();
        newMain();
    }

    private static void newMain() {
        try (DBServiceHibernateImpl dbService = new DBServiceHibernateImpl()){
            dbService.save(new UserDataSet("User1",18, new ArrayList<>(Arrays.asList(new PhoneDataSet("111"),new PhoneDataSet("333"))), new AddressDataSet("Street1")));
            dbService.save(new UserDataSet("User2",20,  new ArrayList<>(Arrays.asList(new PhoneDataSet("222"),new PhoneDataSet("444"))), new AddressDataSet("Street2")));

            UserDataSet dataSet = dbService.read(1);
            System.out.println(dataSet);

            List<PhoneDataSet> phones = dbService.readAllPhones();
            phones.forEach(System.out::println);

            List<UserDataSet> users = dbService.readAllUsers();
            users.forEach(System.out::println);

            dbService.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void oldMain(){
        try (DBServiceImpl dbService = new DBServiceImpl()) {
            dbService.prepareTables();
            UserDataSet user1 = new UserDataSet("Name1", 18);
            UserDataSet user2 = new UserDataSet("Name2", 19);
            dbService.save(user1);
            dbService.save(user2);
            System.out.println(user1);
            System.out.println(user2);
            user1.setName("newName");
            System.out.println(user1);
            dbService.save(user1);
            UserDataSet newUser = dbService.read(user1.getId(), UserDataSet.class);
            System.out.println("loaded user:" + newUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
