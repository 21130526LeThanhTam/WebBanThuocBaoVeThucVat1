package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReview {
    int id;
    int id_product;
    int id_user;
    int rating; //rating từ 1-5
    String content;
    Date create_at;
}
