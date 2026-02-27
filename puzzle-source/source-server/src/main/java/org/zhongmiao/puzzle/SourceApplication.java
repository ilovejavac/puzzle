package org.zhongmiao.puzzle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Source Service Application
 */
@Slf4j
@SpringBootApplication
public class SourceApplication {

    public static void main(String[] args) {

        SpringApplication.run(SourceApplication.class, args);
        log.info("====> SourceApplication started <====");
    }

}
