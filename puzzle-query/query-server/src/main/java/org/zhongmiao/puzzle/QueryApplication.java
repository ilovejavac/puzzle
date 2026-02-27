package org.zhongmiao.puzzle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Query Service Application
 */
@Slf4j
@SpringBootApplication
public class QueryApplication {

    public static void main(String[] args) {

        SpringApplication.run(QueryApplication.class, args);
        log.info("====> QueryApplication started <====");
    }

}
