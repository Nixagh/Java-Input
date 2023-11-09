# Java-Input

JDK 11

## 1. Scanner
ContentInputApplication.java 
```java
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
                // need change folder
                var folder = "D:\\Work\\.Data\\Grade10_802910";
                runProcess.setFolder(folder);
                runProcess.init();
                runProcess.run();
            };
    }
}
```
