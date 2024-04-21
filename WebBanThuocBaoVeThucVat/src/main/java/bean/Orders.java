package bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Orders {
    private int idUser;
    private float totalPrice;
    private float shippingFee;
    private String address;
    private String phoneNumber;
    private int status;

    private List<CartItem> lp;

    public Orders(int idUser, float totalPrice, float shippingFee, String address,
                  String phoneNumber, int status, List<CartItem> lp) {
        this.idUser = idUser;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.lp = lp;
    }

    public List<CartItem> getLp() {
        return lp;
    }

    public void setLp(List<CartItem> lp) {
        this.lp = lp;
    }



    public int getIdUser() {
        return idUser;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public float getShippingFee() {
        return shippingFee;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public int getStatus() {
        return status;
    }



    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setShippingFee(float shippingFee) {
        this.shippingFee = shippingFee;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public void setStatus(int status) {
        this.status = status;
    }
}
