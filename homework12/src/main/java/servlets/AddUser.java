package servlets;

import dataSet.UserDataSet;
import dbService.DBService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddUser extends HttpServlet {

    private static final String ADDUSER_PAGE_TEMPLATE = "AddUser.html";

    private final TemplateProcessor templateProcessor;
    private final DBService dbService;

    @SuppressWarnings("WeakerAccess")
    public AddUser(TemplateProcessor templateProcessor, DBService dbService) {
        this.dbService = dbService;
        this.templateProcessor = templateProcessor;
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
