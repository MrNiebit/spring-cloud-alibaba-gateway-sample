package cn.lacknb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h2>  </h2>
 *
 * @description:
 * @menu
 * @author: gitsilence
 * @description:
 * @date: 2023/2/15 16:24
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/access")
    public String access() {
        return "hello user-service spring boot => port=9001";
    }

}
