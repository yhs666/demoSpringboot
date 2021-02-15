package cn.test.demo; /*
 * @author: Max Yang
 * @date: 2021-01-29 13:00
 * @desc:
 */

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class LoggerTest {

    @Test
    void  test1(){
        String name="imooc";
        String pass="ssdd";
        log.info("info");
        log.error("name: {}, passwod: {}",name,pass);
    }
}
