package org.zhongmiao.puzzle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Engine Service Application
 */
@Slf4j
@SpringBootApplication
public class EngineApplication {

    public static void main(String[] args) {

        SpringApplication.run(EngineApplication.class, args);
        log.info("====> EngineApplication started <====");
    }

}
