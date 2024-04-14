package bean;

import dao.ProductsDao;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Products implements Serializable {
    private int id;
    private int id_category;
    private int id_discount;
    private String product_name;
    private String image;
    private int price;
    private String des;
    private int status;

    //SELECT id , product_name ,picture, price, id_category, quanitity, status, specifications,pro_desc FROM product


    public int getId() {
        return id;
    }

    public int getId_category() {
        return id_category;
    }

    public int getId_discount() {
        return id_discount;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public String getDes() {
        return des;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public void setId_discount(int id_discount) {
        this.id_discount = id_discount;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Products(int id, int id_category, int id_discount, String product_name, String image, int price, String des, int status) {
        this.id = id;
        this.id_category = id_category;
        this.id_discount = id_discount;
        this.product_name = product_name;
        this.image = image;
        this.price = price;
        this.des = des;
        this.status = status;
    }

    public Products() {
    }

//    public Products(int id, String product_name, String picture, int price, int id_category) {
//        this.id = id;
//        this.product_name = product_name;
//        this.picture = picture;
//        this.price = price;
//        this.id_category = id_category;
//    }

    public Products(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", id_category=" + id_category +
                ", id_discount=" + id_discount +
                ", product_name='" + product_name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", des='" + des + '\'' +
                ", status=" + status +
                '}';
    }

    public String cateOfProduct(){
        String rs= ProductsDao.CateOfProduct(id);
        return rs;
    }

    public String formatPrice() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(price);
        return formattedPrice.replace(',', '.');
    }
    public String statusString(){
        if(this.status==1){
            return "Mở bán";
        }
        return "Hủy bán";
    }
    public static void main(String[] args) {
        Products a=new Products(4000000);
//        System.out.println(a.formatPrice());

    }

}