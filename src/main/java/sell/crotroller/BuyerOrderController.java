package sell.crotroller;

import sell.VO.ResultVO;
import sell.converter.OrderForm2OrderDTOConverter;
import sell.dto.OrderDTO;
import sell.enmu.ResultEnum;
import sell.exception.SellException;
import sell.form.OrderForm;
import sell.service.BuyerService;
import sell.service.OrderService;
import sell.service.impl.PushMessageServiceImpl;
import sell.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;


    @Autowired
    private BuyerService buyerService;

    @Autowired
    PushMessageServiceImpl pushMessageService;
    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>>create(@Valid OrderForm orderForm,
                                              BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确");
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[创建订单]购物车不能为空");
            throw  new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult=orderService.create(orderDTO);

        Map<String,String>map=new HashMap<>();
        map.put("orderId",createResult.getOrderId());
//
//        pushMessageService.orderStatus(orderDTO);

        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>>list(@RequestParam("openid")String openid,
    @RequestParam(value = "page",defaultValue = "0")Integer page,
    @RequestParam(value = "size",defaultValue = "10")Integer size){

        if(StringUtils.isEmpty(openid)){
            log.error("查询订单列表opnid为空");
        }
        PageRequest request=new PageRequest(page,size);
        Page<OrderDTO> orderDTOpage=orderService.findList(openid,request);

//        orderDTOpage.get

        return ResultVOUtil.success(orderDTOpage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public  ResultVO<OrderDTO> detail(@RequestParam("openid")String openid,
                                      @RequestParam("orderId")String orderId){
        //TO不安全的


        return ResultVOUtil.success(buyerService.findOrderOne(openid,orderId));

    }

    //取消订单
    @GetMapping("/cancel")
    public  ResultVO<OrderDTO> cancel(@RequestParam("openid")String openid,
                                      @RequestParam("orderId")String orderId){
        //TODI 不安全的
        OrderDTO orderDTO=buyerService.cancelOrder(openid,orderId);

        return ResultVOUtil.success();

    }
}
