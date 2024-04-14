package controller;

import bean.User;
import bean.Users;
import db.DBProperties;
import org.apache.http.client.ClientProtocolException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "loginByGoogle",value = "/loginByGoogle")
public class LoginGoogleHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        String code=req.getParameter("code");
        String accessToken =getToken(code);
        try {
            Users user= getUserInfo(accessToken);
            System.out.println(accessToken);
            System.out.println(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //4%2F0AeaYSHAk-qshsZIeHG32Cogy6bVnLeQRzUWjcZ9srNZ-w-aXgGcOU_MNsTa2UdyOS50YrQ
    }
    public static String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(DBProperties.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", DBProperties.GOOGLE_CLIENT_ID)
                        .add("client_secret", DBProperties.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", DBProperties.GOOGLE_REDIRECT_URI)
                        .add("code", code)
                        .add("grant_type", DBProperties.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = (JsonObject) (new Gson()).fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static Users getUserInfo(String accessToken) throws ClientProtocolException, IOException, SQLException {
        String link = DBProperties.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonElement key = jsonObject.get("id");
        Users googlePojo = (Users) (new Gson()).fromJson(response, Users.class);
        return googlePojo;
    }
}
