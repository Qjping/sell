package sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
@Slf4j
@Component
public class RedisLock {
    @Autowired
    StringRedisTemplate redisTemplate;

    public Boolean lock(String key,String value){
        if(redisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }
        String currentValue = redisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(currentValue)&Long.parseLong(currentValue)<System.currentTimeMillis()){
            String oldValue = redisTemplate.opsForValue().getAndSet(key,value);
            if(!StringUtils.isEmpty(currentValue)&&oldValue.equals(currentValue) ){
                return  true;
            }
        }
        return false;
    }

    public void unlock(String key,String value){

        try {
           String currenValue = redisTemplate.opsForValue().get(key);
           if(!StringUtils.isEmpty(currenValue)&&currenValue.equals(currenValue)){
               redisTemplate.opsForValue().getOperations().delete(key);
           }
        }catch (Exception e){
            log.error("[redis分布式解锁]异常，{}",e);
        }

    }
}
