package com.nixagh.contentinput;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nixagh.contentinput.repository.PassageRepository;
import com.nixagh.contentinput.repository.QuestionRepository;
import com.nixagh.contentinput.service.SP.DefinitionService;
import com.nixagh.contentinput.service.SP.VisualService;
import com.nixagh.contentinput.service.VWABaseService;
import com.nixagh.contentinput.service.SP.WordStudyService;
import com.nixagh.contentinput.util.ExcelReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


@SpringBootApplication
@Slf4j
public class ContentInputApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentInputApplication.class, args);
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
            var folder = "D:\\Work\\.Data\\Grade10_802910";
            runProcess.setFolder(folder);
            runProcess.init();
            runProcess.run();
        };
    }
}

