package ru.otus.hw14.servlets;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.otus.hw14.dbService.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configurable
public class GetNameByID extends HttpServlet {
    private static final String GETNAME_PAGE_TEMPLATE = "GetNameByID.html";

    @Autowired
    private TemplateProcessor templateProcessor;
    @Autowired
    private DBService dbService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        if (request.getParameter("get_name") != null) {
            String userId = request.getParameter("id");
            if (!userId.equals("")) {
                try {
                    String name = dbService.read(Long.parseLong(userId)).getName();
                    pageVariables.put("name", name);
                } catch (Exception e) {
                    pageVariables.put("name", "User not found");
                }
            } else
                pageVariables.put("isSearchError", "No user ID specified");
        }


        response.setContentType("text/html;charset=utf-8");
        String page = templateProcessor.getPage(GETNAME_PAGE_TEMPLATE, pageVariables);
        response.getWriter().println(page);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
