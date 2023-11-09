package com.nixagh.contentinput.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * @author nghia.nguyen-dinh
 * @since 11/9/2023 at 1:11 PM
 */
@Component
@Slf4j
public class StageInitializer implements ApplicationListener<UIApplication.StageReadyEvent> {
    @Value("classpath:/FXML/ui.fxml")
    private Resource uiResource;

    private final String title;

    private final ApplicationContext applicationContext;

    public StageInitializer(
        @Value("${spring.application.ui.title}") String title,
        ApplicationContext applicationContext
    ) {
        this.title = title;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(@NotNull UIApplication.StageReadyEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(uiResource.getURL());
            loader.setControllerFactory(applicationContext::getBean);
            Parent parent = loader.load();
            Stage state = event.getStage();
            Scene screen = new Scene(parent, 400, 600);

            state.setTitle(title);
            state.getIcons().add(new Image("/assets/logo/128.png"));
            state.setScene(screen);
            state.show();
        } catch (Exception e) {
            log.error("Error when load UI", e.getCause());
        }
    }
}
