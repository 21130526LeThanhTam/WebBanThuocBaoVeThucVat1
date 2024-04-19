package dao;

import Service.ProductsService;
import bean.User;
import db.DBContext;
import db.JDBIConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AccountDAO {
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
    //int id, String username, String password, String phone, String email, String surname, String lastname, int role, String hash) {
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
        String sql = "select id, role,user_name, password, phone, email, sur_name, last_name, hash, active from users where email = ?";
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
    // kiểm tra ng dùng.
    public User checExistUser(String email){
        Optional<User> user = JDBIConnector.getJdbi().withHandle(handle ->
                handle.createQuery("select id, role,user_name, password, phone, email, sur_name, last_name, hash, active from users where email = ?")
                        .bind(0, email)
                        .mapToBean(User.class).stream().findFirst());
        return user.isEmpty() ? null : user.get();
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

//    public String loginGoogle(String email,String username,String surname,String lastname,String hash,String picture){
//        String sql = "insert into users(user_name, password, phone, email, sur_name, last_name, hash, role, active) values (?,?,?,?,?,?,?,0,0)";
//        Connection conn = DBContext.getConnection();
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, username);
//            ps.setString(2, pass);
//            ps.setString(3, phone);
//            ps.setString(4, email);
//            ps.setString(5, surname);
//            ps.setString(6, lastname);
//            ps.setString(7, hash);
//            int i = ps.executeUpdate();
//            if(i != 0){
//                return "success";
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }


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

    public static void main(String[] args) {
        System.out.println(AccountDAO.getInstance().checExistUser("abc@gmail.com"));
    }
}
