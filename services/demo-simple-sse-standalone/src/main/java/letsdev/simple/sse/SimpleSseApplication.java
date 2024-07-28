package letsdev.simple.sse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import static letsdev.constants.CommonConstants.ROOT_PACKAGE;

@SpringBootApplication(scanBasePackages = ROOT_PACKAGE)
@ConfigurationPropertiesScan(basePackages = ROOT_PACKAGE)
public class SimpleSseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleSseApplication.class, args);
    }

}