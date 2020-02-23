//package sell.aspect;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import sell.constant.CookieConstant;
//import sell.constant.RedisConstant;
//import sell.exception.SellerAuthorizeExption;
//import sell.util.CookiesUtil;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
//@Aspect
//@Component
//@Slf4j
//public class SellerAuthorizeAspect {
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Pointcut("execution(public * sell.crotroller.Admin*.*(..))"+
//            "&& ! execution(public * sell.crotroller.SellInfoController.*(..))")
//    public void verify(){
//
//    }
//
//    @Before("verify()")
//    public void doVerify(){
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        //查询cookie
//        Cookie cookie = CookiesUtil.get(request, CookieConstant.TOKEN);
//        if(cookie == null){
//            log.warn("【登录校验】没有token信息");
//            throw  new SellerAuthorizeExption();
//        }
//
//        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
//        if(tokenValue == null ){
//            log.warn("【登录校验】Redis中查不到token");
//            throw  new SellerAuthorizeExption();
//        }
//    }
//}
