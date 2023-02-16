package cn.lacknb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <h2>  </h2>
 *
 * @description:
 * @menu
 * @author: gitsilence
 * @description:
 * @date: 2023/2/15 16:15
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class BizService9002Application {

    public static void main(String[] args) {
        SpringApplication.run(BizService9002Application.class, args);
    }

}
