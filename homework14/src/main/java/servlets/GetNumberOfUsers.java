package servlets;

import dbService.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configurable
public class GetNumberOfUsers extends HttpServlet {
    private static final String GETNUMBER_PAGE_TEMPLATE = "GetNumberOfUsers.html";

    @Autowired
    private TemplateProcessor templateProcessor;
    @Autowired
    private DBService dbService;


    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
        dbService = context.getBean("dbService", DBService.class);
        templateProcessor = context.getBean("templateProcessor", TemplateProcessor.class);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("numberOfUsers", dbService.count());

        response.setContentType("text/html;charset=utf-8");
        String page = templateProcessor.getPage(GETNUMBER_PAGE_TEMPLATE, pageVariables);
        response.getWriter().println(page);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
