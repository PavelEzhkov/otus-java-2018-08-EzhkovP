package servlets;

import dbService.DBService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetNumberOfUsers extends HttpServlet {
    private static final String GETNUMBER_PAGE_TEMPLATE = "GetNumberOfUsers.html";

    private final TemplateProcessor templateProcessor;
    private final DBService dbService;

    @SuppressWarnings("WeakerAccess")
    public GetNumberOfUsers(TemplateProcessor templateProcessor, DBService dbService) {
        this.templateProcessor = templateProcessor;
        this.dbService = dbService;
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
