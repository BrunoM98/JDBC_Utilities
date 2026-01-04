package com.dm.tasks.menu;

import com.dm.tasks.TasksApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class SistemFXTask extends Application {

    private ConfigurableApplicationContext applicationContext;

//    public static void main(String[] args) {
//        launch(args);
//    }

    @Override
    public void init() {
//        se ejecuta la fabrica de spring atravez de la llamada en TaskApplication
        this.applicationContext = new SpringApplicationBuilder(TasksApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
//        se carga en memoria la interfaz grafica de javaFX
        FXMLLoader louder = new FXMLLoader(TasksApplication.class.getResource("/template/index.fxml"));
//        fabrica de ojetos tipo controlador
//        se integran las tecnologias de javaFX y springoot
//        getBean obtiene los objetos de spring y los carga en JavaFX
        louder.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(louder.load());
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void stop(){
//        cierre de SpringBoot
        applicationContext.close();
//        cierre de JavaFX
        Platform.exit();
    }
}
