import DataBase.DBService;
import DataSet.UserDataSet;


public class Main {
    public static void main(String[] args) {
        //Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
        try (DBService dbService = new DBService()) {
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
            UserDataSet newUser = dbService.load(user2.getId(), UserDataSet.class);
            System.out.println("loaded user:" + newUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
