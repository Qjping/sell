package sell.repositor;

import sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private  OrderDetailRepository repository;
    @Test
    public void findByOrderId() {
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("123456");
        orderDetail.setOrderId("11111111");
        orderDetail.setProductId("123456");
        orderDetail.setProductIcon("hhttp://sss.jpg");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(1.2));
        orderDetail.setProductQuantity(2);

        OrderDetail result= repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void find(){
        List<OrderDetail> orderDetail=repository.findByOrderId("11111111");
        System.out.println(orderDetail);
        Assert.assertNotNull(orderDetail);
    }
}