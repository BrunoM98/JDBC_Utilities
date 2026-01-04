package com.dm.tasks;

import com.dm.tasks.menu.SistemFXTask;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication {

	public static void main(String[] args) {
//        se hace una llamada desde el main de springboot hacia SistemaFXTask y apartir de esta clase se carga la interfac;
//        pararecuperar datos de la DB se crea el controlador;
//        SpringApplication.run(TasksApplication.class, args);
        Application.launch(SistemFXTask.class, args);
	}

}
