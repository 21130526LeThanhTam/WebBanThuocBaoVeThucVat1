package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderTable {
    private int id;
    private String username;
    private int total_price;
    private int shipping_fee;
    private String address;
    private String phone_number;
    private List<OrderDetailTable> listDetails;
    private Timestamp createAt;
    private String payment_status;
    private int order_status;

}
