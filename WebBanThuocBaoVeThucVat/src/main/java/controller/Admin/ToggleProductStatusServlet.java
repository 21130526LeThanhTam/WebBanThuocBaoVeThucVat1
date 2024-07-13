package controller.Admin;

import Service.ProductsService;
import Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ToggleProductStatusServlet", urlPatterns = {"/disableProduct", "/cancelDisableProduct"})
public class ToggleProductStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productID = request.getParameter("productID");
        String action = request.getRequestURI().substring(request.getContextPath().length());
        boolean disable = action.equals("/disableProduct");
        if (productID != null) {
            try {
                int id = Integer.parseInt(productID);
                // Thực hiện thao tác thay đổi trạng thái sản phẩm trong cơ sở dữ liệu
                boolean result =  ProductsService.getInstance().toggleProductStatus(id,disable);

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
