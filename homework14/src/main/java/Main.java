/*
public class Main {
    private final static int PORT = 8095;
    private final static String PUBLIC_HTML = "public_html";

    public static void main(String[] args) throws Exception {

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        TemplateProcessor templateProcessor = new TemplateProcessor();
        DBService ru.otus.hw14.dbService = new DBServiceHibernateImpl();


        contextHandler.addServlet(new ServletHolder(new AddUser(templateProcessor, ru.otus.hw14.dbService)), "/AddUser");
        contextHandler.addServlet(new ServletHolder(new GetNameByID(templateProcessor, ru.otus.hw14.dbService)), "/GetNameByID");
        contextHandler.addServlet(new ServletHolder(new GetNumberOfUsers(templateProcessor, ru.otus.hw14.dbService)), "/GetNumberOfUsers");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, contextHandler));

        server.start();
        server.join();

    }

}*/
