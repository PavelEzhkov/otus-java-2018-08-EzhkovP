package ru.otus.hw14.servlets;

import ru.otus.hw14.dataSet.UserDataSet;
import ru.otus.hw14.dbService.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Configurable
public class AddUser extends HttpServlet {

    private static final String ADDUSER_PAGE_TEMPLATE = "AddUser.html";

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
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("submit_add") != null) {
            String userName = request.getParameter("name");
            String userAge = request.getParameter("age");
            UserDataSet userDataSet = new UserDataSet(userName, Integer.parseInt(userAge));
            try {
                dbService.save(userDataSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        response.setContentType("text/html;charset=utf-8");
        String page = templateProcessor.getPage(ADDUSER_PAGE_TEMPLATE, null);
        response.getWriter().println(page);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
