package sell.crotroller;


import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sell.dataobject.OrderDetail;
import sell.dto.OrderDTO;
import sell.enmu.ResultEnum;
import sell.exception.SellException;
import sell.service.OrderService;
import sell.service.PayService;

import java.util.Map;

@Controller
@RequestMapping("/pay")
public class PayContontroller {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId")String id,
                               @RequestParam("returnUrl")String returnUrl,
                               Map<String,Object> map){

        //查询订单
        OrderDTO orderDTO=orderService.findOne(id);
        if(null == orderDTO){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //发起支付
        PayResponse payResponse=payService.create(orderDTO);
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);

        return new ModelAndView("pay/create",map);
    }

    @PostMapping("notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);
        return new ModelAndView("pay/success");
    }



}
