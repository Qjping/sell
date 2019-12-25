package sell.crotroller;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sell.service.OrderService;

import java.util.Map;

@RestController
@RequestMapping("/seller/order")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam("page") Integer page,
                             @RequestParam("size") Integer size,
                             Map<String,Object> map){

        PageRequest pageRequest = new PageRequest(page,size);
        map.put("orderDTOPage",orderService.findList(pageRequest));
        return new ModelAndView("order/list",map);



    }
}
