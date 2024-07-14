package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailTable {
    private int id;
    private String product_name;
    private String img;
    private int quantity;
    private int priceDetails;
}
