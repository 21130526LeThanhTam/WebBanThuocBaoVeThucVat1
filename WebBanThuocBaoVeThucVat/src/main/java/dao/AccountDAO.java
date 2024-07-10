package dao;

import bean.User;
import db.DBContext;
import log.AbstractDao;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AccountDAO extends AbstractDao<User> {
    private static AccountDAO instance;

    public static AccountDAO getInstance() {
        if(instance ==null) instance = new AccountDAO();
        return instance;
    }

    public static User login(String email, String pass) {
        String sql = "SELECT id, role, user_name, password, phone, email, sur_name, last_name, hash, active FROM users WHERE email = ? AND password = ? AND active = 1 AND login_by = ?";
        Connection conn = DBContext.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            ps.setInt(3, 0);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new User(
                        rs.getInt("id"),
                        rs.getInt("role"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("sur_name"),
                        rs.getString("last_name"),
                        rs.getString("hash"),
                        rs.getInt("active")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public User checkAccountExist(String email, Integer loginBy) {
        String sql = "select id, role,user_name, password, phone, email, sur_name, last_name, hash, active from users where email = ? and login_by = ?";
        Connection conn = DBContext.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setInt(2, loginBy);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new User(
                        rs.getInt("id"),
                        rs.getInt("role"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("sur_name"),
                        rs.getString("last_name"),
                        rs.getString("hash"),
                        rs.getInt("active")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String signUp(String email,String pass,String username,String surname,String lastname,String phone,String hash) {
        String sql = "insert into users(user_name, password, phone, email, sur_name, last_name, hash, role, active) values (?,?,?,?,?,?,?,0,0)";
        Connection conn = DBContext.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, pass);
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.setString(5, surname);
            ps.setString(6, lastname);
            ps.setString(7, hash);
            int i = ps.executeUpdate();
            if(i != 0){
                return "success";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String signUp2(String email, String pass, String username, String surname, String lastname, String phone, String hash, Integer login_By){
        String sql = "insert into users(user_name, password, phone, email, sur_name, last_name, hash, role, active, login_by) values (?,?,?,?,?,?,?,0,1,?)";
        Connection conn = DBContext.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, pass);
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.setString(5, surname);
            ps.setString(6, lastname);
            ps.setString(7, hash);
            ps.setInt(8, login_By);
            int i = ps.executeUpdate();
            if(i != 0){
                return "success";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }



    public String activeAccount(String email, String hash){
        Connection con = DBContext.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("select email, hash, active from users where email = ? and hash = ? and active = 0 and login_by = 0");
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
        String sql = "update users set password = ? where email = ? and login_by = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            // Trong câu lệnh SQL UPDATE, chúng ta cần set password = ? và email = ?
            ps.setString(1, newPassword); // Chỗ này sử dụng newPassword thay vì email
            ps.setString(2, email);
            ps.setInt(3, 0);// Chỗ này sử dụng email thay vì newPassword
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