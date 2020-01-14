package sell.crotroller;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sell.dto.OrderDTO;
import sell.enmu.ResultEnum;
import sell.exception.SellException;
import sell.service.OrderService;

import java.util.Map;

@RestController
@RequestMapping("/seller/order")
@Slf4j
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String,Object> map){

        PageRequest pageRequest = new PageRequest(page-1,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        map.put("currentPage",page);
        map.put("orderDTOPage",orderDTOPage);
        map.put("size",size);

        return new ModelAndView("order/list");
    }

    @GetMapping("/cancel")
    public ModelAndView cancle(@RequestParam("orderId")String orderId,
                               Map<String,Object> map){

        try{
            OrderDTO order = orderService.findOne(orderId);
            orderService.cancel(order);

        }catch (SellException e){
            log.info("[取消订单异常]:{}"+e);
            map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
            map.put("url","/sell/seller/order/list");
            return  new ModelAndView("common/error", map);
        }
        map.put("msg",ResultEnum.SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return  new ModelAndView("common/success", map);
    }

        @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId")String orderId,
                               Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try{
             orderDTO = orderService.findOne(orderId);
        }catch (SellException e){
            log.error("【买家端查询订单异常】{}"+e);
            map.put("msg",ResultEnum.ORDER_DETAIL_NOT_EXIST.getMessage());
            map.put("url","/sell/seller/order/list");
            return  new ModelAndView("common/error", map);
        }
        map.put("orderDTO",orderDTO);
        return  new ModelAndView("order/detail", map);
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId")String orderId,
                               Map<String,Object> map){

        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("【买家端取消订单异常】"+e);
            map.put("msg",ResultEnum.ORDER_UPDATE_FAIL);
            map.put("url","/sell/seller/order/list");
            return  new ModelAndView("common/error", map);
        }
        map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return  new ModelAndView("common/success", map);
    }
}
