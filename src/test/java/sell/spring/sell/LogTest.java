package sell.spring.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogTest {
//    private  final Logger logger= LoggerFactory.getLogger(LogTest.class);
    @Test
    public void test(){
        String name="name";
        String pwd="pwd";
        System.out.printf("%s",name);
        log.info("name:{},pwd:{}",name,pwd);
        log.debug("debug");
        log.info("info");
        log.error("error");
    }
}
