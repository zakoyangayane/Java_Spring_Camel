package com.task;

import com.task.endpoint.ImportExport;
import org.apache.camel.CamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TaskApplication {

    static ImportExport importExport = new ImportExport();

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
        importExport.execute();
//        CamelContext camelContext = new DefaultCamelContext();
//        try {
//            camelContext.addRoutes(new FtpRouteBuilder());
//            camelContext.start();
//            // do other stuff...
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            stopCamelContext(camelContext);
//        }
    }

    private static void stopCamelContext(CamelContext camelContext) {
        try {
            camelContext.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
