package dao;

import bean.User;
import db.DBContext;
import db.JDBIConnector;
import log.AbsModel;
import log.AbstractDao;
import org.apache.commons.codec.digest.Md5Crypt;
import utils.PasswordUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


public class AccountDAO extends AbstractDao<User> {

    public AccountDAO() {
    }

    private static AccountDAO instance;
    public static AccountDAO getInstance(){
        if(instance ==null ) instance = new AccountDAO();
        return instance;
    }
    //Phương thức lấy ra id cao nhất.
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

    public User loginAccount(AbsModel model, String ip, int level, String address) {
        User user = (User) model;
        User u = null;
        String sql = "SELECT id, role, user_name, password, phone, email, sur_name, last_name, hash, active FROM users WHERE email = ? AND active = 1";
        List<User> users = JDBIConnector.getJdbi().withHandle(handle ->
                handle.createQuery(sql).bind(0, user.getEmail()).mapToBean(User.class).stream().collect(Collectors.toList())
        );
        if (users.size() != 1) {
             super.login(user, "Login failed!", ip, level, address);
        } else {
            u = users.get(0);
            if(PasswordUtils.verifyPassword(user.getPassword(), u.getPassword())){
                super.login(user,"Login success!",ip,level,address);
            } else {
                super.login(user,"Login failed!",ip,level,address);
                u = null;
            }
        }
        return u;
    }

    public User checkAccountExist(String email){
        String sql = "select id, role,user_name, password, phone, email, sur_name, last_name, hash, active from users where email = ?";
        Connection conn = DBContext.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
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


    public String signUp(String email,String pass,String username,String surname,String lastname,String phone,String hash){
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
    public String signUp2(String email,String pass,String username,String surname,String lastname,String phone,String hash){
        String sql = "insert into users(user_name, password, phone, email, sur_name, last_name, hash, role, active) values (?,?,?,?,?,?,?,0,1)";
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



    public String activeAccount(String email, String hash){
        Connection con = DBContext.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("select email, hash, active from users where email = ? and hash = ?");
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

    public static void main(String[] args) {
        String email= "abc12@gmail.com";
        String pass= "4297f44b13955235245b2497399d7a93";
        User a = new User();
        a.setEmail(email);
        a.setPassword(pass);
        System.out.println(AccountDAO.getInstance().loginAccount(a,"0:0:0:0:0:0:0:1",1,"06 Le Thi Hong Gam"));
    }
}