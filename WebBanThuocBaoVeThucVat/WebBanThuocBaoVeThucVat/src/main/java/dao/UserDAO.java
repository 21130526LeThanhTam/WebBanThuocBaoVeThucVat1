package dao;

import bean.User;
import db.DBContext;
import db.JDBIConnector;
import log.AbstractDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDAO extends AbstractDao<User> {
    private static UserDAO instance;
    public static UserDAO getInstance(){
        if(instance ==null) instance= new UserDAO();
        return instance;
    }
    public String userChangeInfo(String surname, String lastname, String username, String phone,String email){
        Connection conn = DBContext.getConnection();
        String sql = "update users set surname=? , lastname=? , username=? , phone=? where email=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, surname);
            ps.setString(2, lastname);
            ps.setString(3, username);
            ps.setString(4, phone);
            ps.setString(5, email);

            int i = ps.executeUpdate();

            if(i > 0) return "success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String userChangePassword(String email ,String newPassword){
        Connection conn = DBContext.getConnection();
        String sql = "update users set password = ? where email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setString(2, email);

            int i = ps.executeUpdate();
            if(i > 0) return "success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    //1. lấy người dùng theo email. đã check
    public static User getUserByEmail(String email){
        Optional<User> user = JDBIConnector.getJdbi().withHandle((handle ->
                handle.createQuery("select id,username,password,phone,email,surname,lastname,role,hash,active from users where email = ?")
                        .bind(0,email)
                        .mapToBean(User.class).stream().findFirst()
        ));
        return user.isEmpty() ? null : user.get();
    }
  //2. lấy người dùng theo id. đã check
    public static User selectUser(int id){
        Optional<User> user = JDBIConnector.getJdbi().withHandle(handle ->
                handle.createQuery("select id,user_name,password,phone,email,sur_name,last_name,role,hash,active from users where id = ?")
                        .bind(0, id)
                        .mapToBean(User.class).stream().findFirst());
        return user.isEmpty() ? null : user.get();
    }

//    //3. lấy ra all user. đã check
    public static List<User> dsUsers(){
        List<User> usersL = JDBIConnector.getJdbi().withHandle(handle ->
                handle.createQuery("select id,user_name,password,phone,email,sur_name,last_name,role,hash,active from users").mapToBean(User.class).collect(Collectors.toList()));
        return usersL;
    }
//    //xóa ng dùng theo email.đã check
    public static void deleteUser(int id){// đã check
        JDBIConnector.getJdbi().useHandle(handle ->
                handle.createUpdate("DELETE FROM users WHERE id = ?")
                        .bind(0,id)
                        .execute());
    }
//    // thêm người dùng.đẫ check
    public static void insertUser(String email, String pass, String username, int role, String surname, String lastname, String phone, String hash, int active) {
        JDBIConnector.getJdbi().useHandle(handle ->
                handle.createUpdate("INSERT INTO users(email, password, username, role, surname, lastname, phone, hash, active) VALUES (?,?,?,?,?,?,?,?,?)")
                        .bind(0, email)
                        .bind(1, pass)
                        .bind(2, username)
                        .bind(3, role)
                        .bind(4, surname)
                        .bind(5, lastname)
                        .bind(6, phone)
                        .bind(7, hash)
                        .bind(8, active)
                        .execute()
        );
    }
    //
////    UPDATE `users` SET `email`='dinhvu@gmail.com',`pass`='123dc',`name`='Dinh Vu',`role`=0 WHERE`id`=2;
    // thay đổi thông tin người dùng.
        public static void updateUser(String surname,String lastname,String username,String phone,int active,int id) {
        JDBIConnector.getJdbi().useHandle(handle ->
                handle.createUpdate("UPDATE users SET sur_name=?,last_name=?,user_name=?,phone=?,active=? WHERE id=?")
                        .bind(0,surname)
                        .bind(1,lastname)
                        .bind(2,username)
                        .bind(3,phone)
                        .bind(4,active)
                        .bind(5,id)
                        .execute()
        );
    }

//// kiểm tra người dùng tồn tại.nếu người dùng ko tồn tại false và ngc lại
    public static boolean isUserExists(String email) {
        User a= UserDAO.getUserByEmail(email);
        return a !=null;
    }
//
   // lấy ra số lượng của của từng vai trò
    public static int numOfRole(int role,String search){
        Integer integer = JDBIConnector.getJdbi().withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM users where role=? AND (last_name LIKE ? OR user_name LIKE ?)")
                        .bind(0,role)
                        .bind(1, "%" + search + "%")
                        .bind(2, "%" + search + "%")
                        .mapTo(Integer.class)
                        .one());
        return integer != null ?integer :0;
    }
//    // Lấy ra 10 người .
    public static List<User>selectTen(int index){
        List<User> users = JDBIConnector.getJdbi().withHandle(handle ->
                handle.createQuery("SELECT id,username,password,phone,email,surname,lastname,role,hash,active FROM users\n" +
                                "ORDER BY id\n" +
                                "LIMIT 5 OFFSET ?")
                        .bind(0,(index - 1) * 5)
                        .mapToBean(User.class)
                        .collect(Collectors.toList()));
        return users;
    }
    // lấy ra 5 người theo role.
    public static List<User>listOfRole(int role,int index){
        List<User> users = JDBIConnector.getJdbi().withHandle(handle ->
                handle.createQuery("SELECT id,role,username,password,phone,email" +
                                ",surname,lastname,hash\n" +
                                "FROM users\n" +
                                "WHERE role = ?\n" +
                                "ORDER BY id\n" +
                                "LIMIT 5 OFFSET ? ")
                        .bind(0,role)
                        .bind(1,(index-1)*5)
                        .mapToBean(User.class)
                        .collect(Collectors.toList()));
        return users;
    }
    public static List<User> listOfRoleWithSearch(int role, int index, String search) {
        List<User> users = JDBIConnector.getJdbi().withHandle(handle ->
                handle.createQuery("SELECT id, role, user_name, password, phone, email"+
                                ",sur_name,last_name,hash\n" +
                                "FROM users\n" +
                                "WHERE role=? AND (last_name LIKE ? OR user_name LIKE ?)\n" +
                                "ORDER BY id\n" +
                                "LIMIT 5\n" +
                                "OFFSET ? ")
                        .bind(0, role)
                        .bind(1, "%" + search + "%")
                        .bind(2, "%" + search + "%")
                        .bind(3, (index - 1) * 5)
                        .mapToBean(User.class)
                        .collect(Collectors.toList()));
        return users;
    }

    @Override
    public boolean selectModel(int id) {
        super.selectModel(id);

        return true;
    }

    @Override
    public boolean insertModel(User model) {
        Integer i =JDBIConnector.getJdbi().withHandle(handle ->
                handle.createUpdate("INSERT INTO users(role, user_name, phone,email, sur_name, last_name, active,password) VALUES (?,?,?,?,?,?,?,?)")
                        .bind(0,model.getRole())
                        .bind(1,model.getUsername())
                        .bind(2,model.getPhone())
                        .bind(3,model.getEmail())
                        .bind(4,model.getSurname())
                        .bind(5,model.getLastname())
                        .bind(6,model.getActive())
                        .bind(7,model.getPassword())
                        .execute()
        );
        super.insertModel(model);
        if(i==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteModel(User model) {
        super.deleteModel(model);
        return true;
    }
    @Override
    public boolean updateModel(User model) {
        Integer i = JDBIConnector.getJdbi().withHandle(handle ->
                handle.createUpdate("UPDATE users SET sur_name=?,last_name=?,user_name=?,phone=?,active=? WHERE id=?")
                        .bind(0,model.getSurname())
                        .bind(1,model.getLastname())
                        .bind(2,model.getUsername())
                        .bind(3,model.getPhone())
                        .bind(4,model.getActive())
                        .bind(5,model.getId())
                        .execute()
        );

        super.updateModel(model);
        if(i==1) return true;
        return false;
    }

    //int id, int active, String username, String phone, String surname, String lastname
    public static void main(String[] args) {
        //int role, int active, String username, String password, String phone, String email, String surname, String lastname, String hash
        User a= new User(2,1,"ll","324","32423","23423");
        User b =new User(1,1,"ddd","sdfs","9084958","ad@gmail.com","dsfsd","dsf");
        UserDAO.getInstance().insertModel(b);
    }
}
