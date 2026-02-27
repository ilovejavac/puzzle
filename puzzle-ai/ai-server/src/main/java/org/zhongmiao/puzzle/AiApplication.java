package org.zhongmiao.puzzle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * AI Service Application
 */
@Slf4j
@SpringBootApplication
public class AiApplication {

    public static void main(String[] args) {

        SpringApplication.run(AiApplication.class, args);
        log.info("====> AiApplication started <====");
    }

}
