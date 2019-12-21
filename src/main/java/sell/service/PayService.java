package sell.service;


import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import org.springframework.stereotype.Service;
import sell.dto.OrderDTO;

@Service
public interface PayService {
    PayResponse create(OrderDTO orderDTO);
    void notify(String notifyData);
    RefundResponse refund(OrderDTO orderDTO);

}