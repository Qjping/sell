package com.muke.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductVo {
    @JsonProperty("name")
    private  String categoryName;
    @JsonProperty("type")
    private  Integer CategoryType;
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVOList;

}
