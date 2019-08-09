package sell.repositor;

import sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private  OrderMasterRepository repository;

    private  final String OPEMID="110110";

    @Test
    public  void saveTest(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("王思想");
        orderMaster.setBuyerAddress("上海");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setBuyerPhone("1562323111");
        orderMaster.setOrderAmount(new BigDecimal(2.3));

        OrderMaster result=repository.save(orderMaster);

        Assert.assertNotNull(result);
    }

    @Test
    public void  findByBuyerOpenId(){
        PageRequest request=new PageRequest(1,3);

        Page<OrderMaster> result=repository.findByBuyerOpenid(OPEMID,request);
        Assert.assertNotEquals(0,result.getTotalElements());
        System.out.println(result);
    }
}