package controller;

import Service.SendingEmail;
import bean.User;
import bean.Util;
import dao.AccountDAO;
import db.DBProperties;
import org.apache.http.client.ClientProtocolException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

@WebServlet(name = "loginByGoogle",value = "/loginByGoogle")
public class LoginGoogleHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code=req.getParameter("code");
        String accessToken =getToken(code);
        User user = new User();
        User userCheck = null;
        String myHash ;
        Random random = new Random();
        random.nextInt(999999);
        myHash = DigestUtils.md5DigestAsHex((String.valueOf(random)).getBytes());
        Timestamp currentTimestamp= Util.getCurrentTimestamp();
        HttpSession session = req.getSession();
        try {
            user= getUserInfo(accessToken);
            userCheck= AccountDAO.getInstance().checExistUser(user.getEmail());
            if(userCheck ==null){
                String str = AccountDAO.getInstance().signUp2( user.getEmail(), null, user.getUserName(),user.getSurName() ,user.getLastName() ,user.getPhone(), myHash);
                if(str.equals("success")){
                    session.setAttribute("user", user);
                    resp.sendRedirect("./HomePageController");
                }
            }else{
                session.setAttribute("user", user);
                resp.sendRedirect("./HomePageController");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




        //4%2F0AeaYSHAk-qshsZIeHG32Cogy6bVnLeQRzUWjcZ9srNZ-w-aXgGcOU_MNsTa2UdyOS50YrQ
    }
    public static String getToken(String code) throws IOException {
        String response = Request.Post(DBProperties.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", DBProperties.GOOGLE_CLIENT_ID)
                        .add("client_secret", DBProperties.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", DBProperties.GOOGLE_REDIRECT_URI)
                        .add("code", code)
                        .add("grant_type", DBProperties.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = (new Gson()).fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static User getUserInfo(String accessToken) throws IOException, SQLException {
        String link = DBProperties.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        jsonObject.addProperty("id", AccountDAO.getInstance().GetId());
        JsonElement value = jsonObject.get("given_name");
        JsonElement value2 = jsonObject.get("name");
        jsonObject.add("lastname", value);
        jsonObject.add("username", value2);

        User googlePojo = (new Gson()).fromJson(jsonObject, User.class);
        return googlePojo;
    }
}