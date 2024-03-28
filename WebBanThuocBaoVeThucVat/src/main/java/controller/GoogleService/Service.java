package controller.GoogleService;

import bean.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.sql.SQLException;

import dao.AccountDAO;
import db.DBProperties;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

public class Service {
    public Service() {
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(DBProperties.GOOGLE_LINK_GET_TOKEN).bodyForm(Form.form().add("client_id", DBProperties.GOOGLE_CLIENT_ID).add("client_secret", DBProperties.GOOGLE_CLIENT_SECRET).add("redirect_uri", DBProperties.GOOGLE_REDIRECT_URI).add("code", code).add("grant_type", DBProperties.GOOGLE_GRANT_TYPE).build()).execute().returnContent().asString();
        JsonObject jobj = (JsonObject) (new Gson()).fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

//    public static User getUserInfo(String accessToken) throws ClientProtocolException, IOException, SQLException {
//        String link = DBProperties.GOOGLE_LINK_GET_USER_INFO + accessToken;
//        String response = Request.Get(link).execute().returnContent().asString();
//        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
//        JsonElement key = jsonObject.get("id");
//        jsonObject.addProperty("id",AccountDAO.getInstance().GetId());
//        JsonElement value = jsonObject.get("given_name");
//        jsonObject.add("username", value);
//        User googlePojo = (User) (new Gson()).fromJson(jsonObject, User.class);
//        return googlePojo;
//    }
}