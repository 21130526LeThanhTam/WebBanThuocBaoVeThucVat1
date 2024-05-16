package bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable {
    int id;
    int idUser;
    double totalPrice;
    double shippingFee;
    String address;
    String phone;
    Timestamp create_at;

    public Order(int id, int idUser, double totalPrice, double shippingFee, String address, String phone, Timestamp create_at) {
        this.id = id;
        this.idUser = idUser;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.address = address;
        this.phone = phone;
        this.create_at = create_at;
    }
    public Order( int idUser, double totalPrice, double shippingFee, String address, String phone, Timestamp create_at) {
        this.idUser = idUser;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.address = address;
        this.phone = phone;
        this.create_at = create_at;
    }
    public Order(){

    }

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", totalPrice=" + totalPrice +
                ", shippingFee=" + shippingFee +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", create_at=" + create_at +
                '}';
    }
}
