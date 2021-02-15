package cn.test.demo.ViewObject; /*
 * @author: Max Yang
 * @date: 2021-01-30 10:47
 * @desc:
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductViewObject {
    @JsonProperty("name")
    private  String categoryName;
    @JsonProperty("type")
    private  Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoViewObject> productInfoViewObjectList;
}
