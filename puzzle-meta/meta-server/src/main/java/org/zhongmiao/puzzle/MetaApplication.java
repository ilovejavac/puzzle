package org.zhongmiao.puzzle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Metadata Service Application
 */
@Slf4j
@SpringBootApplication
public class MetaApplication {

    public static void main(String[] args) {

        SpringApplication.run(MetaApplication.class, args);
        log.info("====> MetaApplication started <====");
    }

}
