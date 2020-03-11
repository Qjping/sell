package sell.dataobject.mapper;

import org.apache.ibatis.annotations.*;
import sell.dataobject.ProductCategory;

import java.util.Map;

public interface ProductCategoryMapper {
    @Insert("insert into product_category (category_name,category_type)values(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=VARCHAR})")
    int insertByMap(Map<String,Object> map);

    @Insert("insert into product_category (category_name,category_type)values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=VARCHAR})")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")}
    )
    ProductCategory findByCategoryType (Integer categoryType);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByObject(ProductCategory productCategory);

    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);

    ProductCategory selectByCategoryType(Integer categoryType);

}
