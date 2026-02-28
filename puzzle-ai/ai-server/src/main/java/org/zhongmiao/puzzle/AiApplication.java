package org.zhongmiao.puzzle;

import com.dev.lib.jpa.entity.BaseRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * AI Service Application
 */
@Slf4j
@SpringBootApplication
@EntityScan(basePackages = "org.zhongmiao")
@EnableJpaRepositories(
        repositoryBaseClass = BaseRepositoryImpl.class
)
public class AiApplication {

    public static void main(String[] args) {

        SpringApplication.run(AiApplication.class, args);
        log.info("====> AiApplication started <====");
    }

}
