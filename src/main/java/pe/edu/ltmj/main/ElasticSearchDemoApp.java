package pe.edu.ltmj.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "pe.edu.ltmj" })
public class ElasticSearchDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchDemoApp.class, args);
    }
}
