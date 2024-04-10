package dao;

import bean.User;
import db.DBContext;
import db.JDBIConnector;
import org.springframework.util.DigestUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AccountDAO {
    private static AccountDAO instance;

    public AccountDAO() {
        super();
    }
    public static boolean validate(String pwd) {
        String regrexPassword = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+{}|:\"<>?\\\\/\\[\\]\\-=`~])(?=.{8,})\\S+$\n";
        return pwd.matches(regrexPassword);
    }
    public static AccountDAO getInstance() {
        if (instance == null) {
            instance = new AccountDAO();
        }
        return instance;
    }
    public int GetId() throws SQLException {
        String sql = "SELECT * FROM users WHERE id = (SELECT MAX(id) FROM users)";
        Connection conn = DBContext.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public static User login(String email, String pass){
        String sql = "select id, role,username, password, phone, email, surname, lastname, hash, active from users where email = ? and password = ? and active = 1";
        Connection conn = DBContext.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public User checkAccountExist(String email){
        String sql = "select id, role,username, password, phone, email, surname, lastname, hash, active from users where email = ?";
        Connection conn = DBContext.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String signUp(String email, String pass, String username, String surname,
                         String lastname, String phone, String hash, int active, String picture, Timestamp date) throws SQLException {
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp avc = new Timestamp(currentTimeMillis);
        JDBIConnector.getJdbi().withHandle((handle) -> {
return handle.createUpdate("insert into users(id,role, username, password, phone, email, surname, lastname, hash, active, picture, create_at) " +
                "values (?, ?, ?, ?, ?, ?, ?, 0, ?, ?, ?, ?)")
                    .bind(0, this.GetId() + 1)
                    .bind(1, username)
                    .bind(2, pass)
                    .bind(3, phone)
                    .bind(4, email)
                    .bind(5, surname)
                    .bind(6, lastname)
                    .bind(7, hash)
                    .bind(8, active)
                    .bind(9, picture)
                    .bind(10, date)
                    .execute();
        });
        return "success";
    }

    public String activeAccount(String email, String hash){
        Connection con = DBContext.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement("select email, hash, active from users where email = ? and hash = ? and active = 0");
            ps.setString(1, email);
            ps.setString(2, hash);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                PreparedStatement ps1 = con.prepareStatement("update users set active = 1 where email = ? and hash = ?");
                ps1.setString(1, email);
                ps1.setString(2, hash);
                int i = ps1.executeUpdate();
                if(i == 1){
                    return "success";
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String forgetPassword(String email, String newPassword) {
        Connection con = DBContext.getConnection();
        String sql = "update users set password = ? where email = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            // Trong câu lệnh SQL UPDATE, chúng ta cần set password = ? và email = ?
            ps.setString(1, newPassword); // Chỗ này sử dụng newPassword thay vì email
            ps.setString(2, email); // Chỗ này sử dụng email thay vì newPassword
            int i = ps.executeUpdate();
            if (i > 0) {
                return "Success";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Nên đóng kết nối sau khi sử dụng xong
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
