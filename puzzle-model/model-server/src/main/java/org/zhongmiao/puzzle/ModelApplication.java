package org.zhongmiao.puzzle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Model Service Application
 */
@Slf4j
@SpringBootApplication
public class ModelApplication {

    public static void main(String[] args) {

        SpringApplication.run(ModelApplication.class, args);
        log.info("====> ModelApplication started <====");
    }

}
