package controller.Admin;

import Service.CategoryService;
import Service.ProductsService;
import Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ToggleCategoryStatusServlet", urlPatterns = {"/disableCategory", "/cancelDisableCategory"})
public class ToggleCategoryStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productID = request.getParameter("categoryID");
        String action = request.getRequestURI().substring(request.getContextPath().length());
        boolean disable = action.equals("/disableCategory");
        if (productID != null) {
            try {
                int id = Integer.parseInt(productID);
                // Thực hiện thao tác thay đổi trạng thái category trong cơ sở dữ liệu
                boolean result =  CategoryService.getInstance().toggleCategoryStatus(id,disable);

                if (result) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("Success");
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("Failed to update user status.");
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Invalid user ID.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("User ID is required.");
        }
    }
}