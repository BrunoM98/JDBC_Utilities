package com.dm.tasks.controller;

import com.dm.tasks.model.Task;
import com.dm.tasks.service.TaskService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.control.*;


import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {

    private static final Logger logger =
            LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TaskService taskService;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, Integer> idTaskColumn;

    @FXML
    private TableColumn<Task, String> nameTaskColumn;

    @FXML
    private TableColumn<Task, String> responsiveTaskColumn;

    @FXML
    private TableColumn<Task, String> statusTaskColumn;

//    se utiliza para iniciar la lista de tareas y refrescar apensa se haga un cambio
    private final ObservableList<Task> taskList =
            FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        se utiliza para poder seleccionar una cosa a la vez y no todo junto
        taskTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setUPColumns();
        listTask();
    }

    private void setUPColumns(){
//        como lo indica la funcion se cargan los atributos de nuestro objeto
        idTaskColumn.setCellValueFactory(new PropertyValueFactory<>("idTask"));
        nameTaskColumn.setCellValueFactory(new PropertyValueFactory<>("nameTask"));
        responsiveTaskColumn.setCellValueFactory(new PropertyValueFactory<>("responsible"));
        statusTaskColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

    }

    private void listTask(){
        logger.info("Execute Task List");
        taskList.clear();
//        se obtiene toda la info de la base e datos
        taskList.addAll(taskService.taskList());
//        relaciona el objeto observable y se agrega toda la informacion a la tabla
        taskTable.setItems(taskList);


    }
}
