package sell.crotroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WexinController {
    @GetMapping("/auth")
    public void auth(@RequestParam("code")String code){
       String url= "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxebe3efd8fb84c22c&secret=4956b4a6c41153c843ab158aeb06656f&code="+code+"&grant_type=authorization_code";
        log.info("进入auth方法");
        log.info("code={}",code);
        RestTemplate restTemplate=new RestTemplate();
        String responce=restTemplate.getForObject(url,String.class);
        log.info(responce);
    }
}
