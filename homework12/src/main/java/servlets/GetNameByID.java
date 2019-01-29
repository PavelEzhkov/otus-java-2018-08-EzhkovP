package servlets;

import dbService.DBService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetNameByID extends HttpServlet {
    private static final String GETNAME_PAGE_TEMPLATE = "GetNameByID.html";

    private final TemplateProcessor templateProcessor;
    private final DBService dbService;


    @SuppressWarnings("WeakerAccess")
    public GetNameByID(TemplateProcessor templateProcessor, DBService dbService) {
        this.templateProcessor = templateProcessor;
        this.dbService = dbService;
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
