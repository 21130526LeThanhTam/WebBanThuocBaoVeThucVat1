package controller.Admin;

import Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ToggleUserStatusServlet", urlPatterns = {"/disableUser", "/cancelDisableUser"})
public class ToggleUserStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String action = request.getRequestURI().substring(request.getContextPath().length());
        boolean disable = action.equals("/disableUser");

        if (userID != null) {
            try {
                int id = Integer.parseInt(userID);
                // Thực hiện thao tác thay đổi trạng thái người dùng trong cơ sở dữ liệu
                boolean result = UserService.getInstance().toggleUserStatus(id, disable);

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