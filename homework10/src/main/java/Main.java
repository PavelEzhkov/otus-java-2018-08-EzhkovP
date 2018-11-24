import DataBase.DBService;
import DataBase.Executor;
import DataBase.H2ConnectionHelper;
import DataSet.UserDataSet;
import org.h2.tools.Server;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
        DBService dbService = new DBService();
        dbService.prepareTables();
        Executor executor = new Executor(H2ConnectionHelper.getConnection());
        UserDataSet user1 = new UserDataSet("Name1", 18);
        UserDataSet user2 = new UserDataSet("Name2", 19);
        executor.save(user1);
        executor.save(user2);
        System.out.println(user1);
        System.out.println(user2);
        user1.setName("newName");
        System.out.println(user1);
        executor.save(user1);
        UserDataSet newUser = executor.load(user2.getId(), UserDataSet.class);
        System.out.println("loaded user:" + newUser);
        try {
            dbService.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
