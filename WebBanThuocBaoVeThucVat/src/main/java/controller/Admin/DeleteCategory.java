package controller.Admin;

import Service.CategoryService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "DeleteCategory", value = "/deleteCate")
public class DeleteCategory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        int cateID = Integer.parseInt(req.getParameter("cateID"));
        boolean success = CategoryService.getInstance().deleteCategory(cateID);
        Map<String, String> result = new HashMap<>();
        if (success) {
            result.put("status", "success");
            result.put("message", "Xóa danh mục thành công!");
        } else {
            result.put("status", "error");
            result.put("message", "Xóa danh mục thất bại!");
        }
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(result);
        resp.getWriter().write(jsonResponse);
    }
}
