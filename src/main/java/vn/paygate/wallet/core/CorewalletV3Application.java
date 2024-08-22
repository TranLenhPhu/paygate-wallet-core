package vn.paygate.wallet.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CorewalletV3Application {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", System.getenv("SPRING_PROFILES_ACTIVE"));
        System.out.println("Active Profile: " + System.getProperty("spring.profiles.active"));
        SpringApplication.run(CorewalletV3Application.class, args);
    }

}
