package sell.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sell.dto.OrderDTO;
import sell.enmu.ResultEnum;
import sell.exception.SellException;
import sell.service.OrderService;
import sell.service.PayService;
import sell.util.MathUtil;

import java.math.BigDecimal;

@Service
@Slf4j
public class PayServiceImp implements PayService {
    private static final String ORDER_NAME = "微信点餐订单";
    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
     public PayResponse create(OrderDTO orderDTO){
        PayRequest payRequest = new PayRequest();

        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】发起支付, request={}", JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付, response={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public void notify(String notifyData){
        PayResponse response = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知,payResponce={}",JsonUtil.toJson(response));


        OrderDTO orderDTO = orderService.findOne(response.getOrderId());
        if(null == orderDTO){
            log.info("【微信支付】异步通知,payResponce={}",JsonUtil.toJson(response));
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        if(!MathUtil.equals(orderDTO.getOrderAmount().doubleValue(),response.getOrderAmount()) ){
            log.info("【微信支付】异步通知,payResponce={},OrderAmount={}",JsonUtil.toJson(response),orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        orderService.paid(orderDTO);

    }


    public RefundResponse refund(OrderDTO orderDTO){
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】request={}",refundRequest);
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】response={}",refundResponse);

        return refundResponse;
    }

}
