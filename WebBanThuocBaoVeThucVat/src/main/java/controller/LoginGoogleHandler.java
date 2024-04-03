//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package controller;

import Service.SendingEmail;
import bean.User;
import bean.Util;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.AccountDAO;
import db.DBProperties;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.util.DigestUtils;

@WebServlet(
        urlPatterns = {"/loginByGoogle"}
)
public class LoginGoogleHandler extends HttpServlet {
    public LoginGoogleHandler() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String accessToken = getToken(code);
        User user = null;
        User userCheck = null;
        Timestamp currentTimestamp = Util.getCurrentTimestamp();

        try {
            user = getUserInfo(accessToken);
            userCheck = AccountDAO.getInstance().checkAccountExist(user.getEmail());
        } catch (SQLException var14) {
            throw new RuntimeException(var14);
        }

        if (user != null) {
            if(userCheck == null) {
                Random random = new Random();
                random.nextInt(999999);
                String myHash = DigestUtils.md5DigestAsHex(("" + String.valueOf(random)).getBytes());
                String str = null;

                try {
                    AccountDAO.getInstance().signUp(user.getEmail(), (String)null, user.getUsername(), (String)null, (String)null, (String)null, myHash, 1, (String)null, Util.formatTimestamp(currentTimestamp));
                } catch (SQLException var13) {
                    throw new RuntimeException(var13);
                }
            }
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("./HomePageController");
        }

    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(DBProperties.GOOGLE_LINK_GET_TOKEN).bodyForm(Form.form().add("client_id", DBProperties.GOOGLE_CLIENT_ID).add("client_secret", DBProperties.GOOGLE_CLIENT_SECRET).add("redirect_uri", DBProperties.GOOGLE_REDIRECT_URI).add("code", code).add("grant_type", DBProperties.GOOGLE_GRANT_TYPE).build()).execute().returnContent().asString();
        JsonObject jobj = (JsonObject)(new Gson()).fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static User getUserInfo(String accessToken) throws ClientProtocolException, IOException, SQLException {
        String link = DBProperties.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonElement key = jsonObject.get("id");
        jsonObject.addProperty("id", AccountDAO.getInstance().GetId());
        JsonElement value = jsonObject.get("given_name");
        jsonObject.add("username", value);
        User googlePojo = (User)(new Gson()).fromJson(jsonObject, User.class);
        return googlePojo;
    }
}
