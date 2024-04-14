package bean;

public class Users {
    private String id;
    private String email;
    private boolean verifiles_email;
    private String name;
    private String firstName;
    private String lastName;
    private String picture;
    private String userIpAddress;
    private String userCountry;
    private String userLocation;

    public Users() {
    }

    public Users(String id, String email, boolean verifiles_email, String name, String firstName, String lastName, String picture, String userIpAddress, String userCountry, String userLocation) {
        this.id = id;
        this.email = email;
        this.verifiles_email = verifiles_email;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.userIpAddress = userIpAddress;
        this.userCountry = userCountry;
        this.userLocation = userLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerifiles_email() {
        return verifiles_email;
    }

    public void setVerifiles_email(boolean verifiles_email) {
        this.verifiles_email = verifiles_email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserIpAddress() {
        return userIpAddress;
    }

    public void setUserIpAddress(String userIpAddress) {
        this.userIpAddress = userIpAddress;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", verifiles_email=" + verifiles_email +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", picture='" + picture + '\'' +
                ", userIpAddress='" + userIpAddress + '\'' +
                ", userCountry='" + userCountry + '\'' +
                ", userLocation='" + userLocation + '\'' +
                '}';
    }
}
