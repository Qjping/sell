package sell.handler;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.servlet.ModelAndView;
        import sell.VO.ResultVO;
        import sell.config.ProjectUrlConfig;
        import sell.exception.SellException;
        import sell.exception.SellerAuthorizeExption;
        import sell.util.ResultVOUtil;

@ControllerAdvice
public class SellerExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeExption.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect"
                .concat(projectUrlConfig.getSell())
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("?returnUrl=")
                .concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultVO hanlerSellException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
