package cn.test.demo.controller; /*
 * @author: Max Yang
 * @date: 2021-01-15 7:40
 * @desc:
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {
    @RequestMapping("/hello")
    public  String hello(){
        log.info("Heelow");
        return  "hellow word";
    }
}
