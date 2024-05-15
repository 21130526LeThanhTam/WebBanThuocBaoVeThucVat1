package bean;

import java.util.Date;

public class User {


    private int id;
    private int role;
    private String userName;
    private String password;
    private String phone;
    private String email;
    private String surName;
    private String lastName;
    private String hash;
    private int active;

    public User() {
    }

    public User(int id, int role, String userName, String password, String phone, String email, String surName, String lastName, String hash, int active) {

        this.id = id;
        this.role = role;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.surName = surName;
        this.lastName = lastName;
        this.hash = hash;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }


    public String getUserName() {
        return userName;

    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }




    // Phương thức dưới để xác định vai trò của người theo role
    public String roleString(){
        if(this.role ==0){
            return "User";
        }else{
            return "Admin";
        }
    }
    // phương thức xác định trạng thái tài khoản.
    public String activeString(){
        if(this.active==1){
            return "Kích hoạt tài khoản";
        }else{
            return "Khóa tài khoản";
        }
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", surName='" + surName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hash='" + hash + '\'' +
                ", active=" + active +
                '}';
    }
}



