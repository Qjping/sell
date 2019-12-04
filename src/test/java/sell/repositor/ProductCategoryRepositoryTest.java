package sell.repositor;

import sell.dataobject.ProductCategory;
import org.hibernate.annotations.DynamicUpdate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@DynamicUpdate
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;
    @Test
    public void  findOneTest(){
        ProductCategory productCategory=repository.findOne(1);

        System.out.println(productCategory.toString());
    }

    @Test
    @Transactional
    public void saveTest(){
        ProductCategory productCategory=new ProductCategory();
        ProductCategory productCategory2=new ProductCategory("邱最爱",3);
        ProductCategory productCategory1=repository.save(productCategory);
        Assert.assertNotEquals("1",productCategory);
        List<Integer>arr= Arrays.asList(1,2,3);
        List<ProductCategory>result=repository.findByCategoryTypeIn(arr);
        System.out.println(result.toString());
        Assert.assertNotEquals(0,result.size());
    }

}