package sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductVo implements Serializable {

    private static final long serialVersionUID = -7279634854134248540L;
    @JsonProperty("name")
    private  String categoryName;
    @JsonProperty("type")
    private  Integer CategoryType;
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVOList;

}
