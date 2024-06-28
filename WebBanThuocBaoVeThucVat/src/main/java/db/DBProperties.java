package db;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Properties;

public class DBProperties {

    private static final Properties prop =new Properties();

    static {
        try {
            prop.load(DBProperties.class.getClassLoader().getResourceAsStream("DB.properties"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public static String host = prop.getProperty("db.host");
    public static String port = prop.getProperty("db.port");
    public static String username = prop.getProperty("db.username");

    public static String password = prop.getProperty("db.password");
    public static String dbName = prop.getProperty("db.name");

    //google


    public static String GOOGLE_CLIENT_ID = prop.getProperty("GOOGLE_CLIENT_ID");
    public static String GOOGLE_CLIENT_SECRET = prop.getProperty("GOOGLE_CLIENT_SECRET");
    public static String GOOGLE_REDIRECT_URI  = prop.getProperty("GOOGLE_REDIRECT_URI");
    public static String GOOGLE_LINK_GET_TOKEN = prop.getProperty("GOOGLE_LINK_GET_TOKEN");
    public static String GOOGLE_LINK_GET_USER_INFO = prop.getProperty("GOOGLE_LINK_GET_USER_INFO");
    public static String GOOGLE_GRANT_TYPE = prop.getProperty("GOOGLE_GRANT_TYPE");
    public static String DISCORD_CLIENT_ID = prop.getProperty("DISCORD_CLIENT_ID");
    public static String DISCORD_CLIENT_SECRET = prop.getProperty("DISCORD_CLIENT_SECRET");
    public static String DISCORD_REDIRECT_URI  = prop.getProperty("DISCORD_REDIRECT_URI");
    public static String DISCORD_LINK_GET_TOKEN = prop.getProperty("DISCORD_LINK_GET_TOKEN");
    public static String DISCORD_LINK_GET_USER_INFO = prop.getProperty("DISCORD_LINK_GET_USER_INFO");


    public static void main(String[] args) {
        System.out.println(GOOGLE_CLIENT_ID);
        System.out.println(GOOGLE_CLIENT_SECRET);
        System.out.println(GOOGLE_REDIRECT_URI);
        System.out.println(GOOGLE_LINK_GET_TOKEN);
        System.out.println(GOOGLE_LINK_GET_USER_INFO);
        System.out.println(GOOGLE_GRANT_TYPE);
//        System.out.println(host);
    }

}