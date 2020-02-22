package sell.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import sell.config.ProjectUrlConfig;
import sell.exception.SellerAuthorizeExption;

@ControllerAdvice
public class SellerExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeExption.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect"
                .concat(projectUrlConfig.getSell())
        .concat(projectUrlConfig.getWechatOpenAuthorize())
        .concat("?returnUrl=")
        .concat("sell/seller/login"));
    }
}
