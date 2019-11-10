package sell.crotroller;

import sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

@RestController
@RequestMapping("wechart")
@Slf4j
public class WechartController {
    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
   public String authorize(@RequestParam("returnUrl")String returnUrl){
        WxMpService wxMpService=new WxMpServiceImpl();
        //1.配置
        String url="http://yier.natapp1.cc/sell/wechat/userInfo";
        String result=wxMpService.oauth2buildAuthorizationUrl(url,WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        log.info("[微信网页授权]获取code,result={}",result);
        //2.调用方法

        return  "redirect:"+result;
    }
    @GetMapping("/userInfo")
    public  void userInfo(@RequestParam("code")String code,
                          @RequestParam("state")String returnUrl){

        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
          wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("[微信网页授权]{}",e);
//            throw  new SellException();
        }
    }

}
