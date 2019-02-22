import dbService.DBService;
import dbService.DBServiceHibernateImpl;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

/*
public class Main {
    private final static int PORT = 8095;
    private final static String PUBLIC_HTML = "public_html";

    public static void main(String[] args) throws Exception {

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        TemplateProcessor templateProcessor = new TemplateProcessor();
        DBService dbService = new DBServiceHibernateImpl();


        contextHandler.addServlet(new ServletHolder(new AddUser(templateProcessor, dbService)), "/AddUser");
        contextHandler.addServlet(new ServletHolder(new GetNameByID(templateProcessor, dbService)), "/GetNameByID");
        contextHandler.addServlet(new ServletHolder(new GetNumberOfUsers(templateProcessor, dbService)), "/GetNumberOfUsers");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, contextHandler));

        server.start();
        server.join();

    }

}*/
