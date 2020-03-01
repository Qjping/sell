package sell.dataobject.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sell.dataobject.ProductCategory;
import sell.dataobject.ProductInfo;
import sell.spring.sell.SellApplicationTests;


import java.util.HashMap;
import java.util.Map;



public class ProductcategoryMapperTest extends SellApplicationTests {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Test
    public void insertByMap() {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("手机");
        productCategory.setCategoryType(99);
        int result = productCategoryMapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);
//        Map<String,Object> map = new HashMap<>();
//        map.put("category_type",222);
//        map.put("category_name","cool");
//        int result = productCategoryMapper.insertByMap(map);
//        Assert.assertEquals(1,result);

    }

    @Test
    public void select(){
        ProductCategory productCategory = productCategoryMapper.findByCategoryType(4);
        Assert.assertNotNull(productCategory);
    }
}