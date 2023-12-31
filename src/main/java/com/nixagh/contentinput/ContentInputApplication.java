package com.nixagh.contentinput;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nixagh.contentinput.UI.UIApplication;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@Slf4j
public class ContentInputApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ContentInputApplication.class, args);
        Application.launch(UIApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public CommandLineRunner commandLineRunner(
        RunProcess runProcess
    ) {
        return args -> {
//            var folder = "D:\\Work\\.Data\\Grade10_802910";
//            runProcess.setFolder(folder);
//            runProcess.init();
//            runProcess.run();
            runProcess.ping();
        };
    }
}

