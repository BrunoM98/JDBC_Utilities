package com.dm.tasks.controller;

import com.dm.tasks.model.Task;
import com.dm.tasks.service.TaskService;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    @FXML
    private TextField nameTaskText;

    @FXML
    private TextField responsibleTaskText;

    @FXML
    private TextField statusTaskText;

    private Integer interIDTask;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        se utiliza para poder seleccionar una cosa a la vez y no todo junto
        taskTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setUPColumns();
        listTask();
    }

    private void setUPColumns() {
//        como lo indica la funcion se cargan los atributos de nuestro objeto
        idTaskColumn.setCellValueFactory(new PropertyValueFactory<>("idTask"));
        nameTaskColumn.setCellValueFactory(new PropertyValueFactory<>("nameTask"));
        responsiveTaskColumn.setCellValueFactory(new PropertyValueFactory<>("responsible"));
        statusTaskColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

    }

    private void listTask() {
        logger.info("Execute Task List");
        taskList.clear();
//        se obtiene toda la info de la base e datos
        taskList.addAll(taskService.taskList());
//        relaciona el objeto observable y se agrega toda la informacion a la tabla
        taskTable.setItems(taskList);
    }

    public void addTask() {
//        evita guardar tareas sin nombre
        if (nameTaskText.getText().isEmpty()) {
            showMessage("Validated Error", "Enter the Task");
            nameTaskText.requestFocus();
            return;
        } else {
//            creamos un objeto tarea que es donde se guardan los datos proporcionados
            Task task = new Task();
//            llenamos el objeto mediante otra funcion privada
            dataForm(task);
//            nos aseguramos de no tener problema con que la variable no sea null
            task.setIdTask(null);
//            utilisamos la funcion de servicio par guardar el objeto tarea
            taskService.saveTask(task);
//            enviamos un mensaje de que se ejecuto bien
            showMessage("Task", "ADD SUCCESSFUL");
//            refrescamos los datos despues del guardado
            refreshForm();
//            listamos nuevamente las tareas para que se visualice la actualizacion
            listTask();
        }
    }

    public void addTskForm() {
//        seleccion de un objeto a l vez
        Task task = taskTable.getSelectionModel().getSelectedItem();
        if (task != null) {
            interIDTask = task.getIdTask();
//              Ingreseamos el valor mediante el set y lo leemos mediante el get, de cada uno de los atributos
            nameTaskText.setText(task.getNameTask());
            responsibleTaskText.setText(task.getResponsible());
            statusTaskText.setText(task.getStatus());
        }
    }

    private void showMessage(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        setiamos titulo
        alert.setTitle(title);
//        header null
        alert.setHeaderText(null);
//        enviamos el mensaje
        alert.setContentText(message);
//        esperamos a que el usuario cierre la ventana
        alert.showAndWait();

    }

    private void dataForm(Task task) {
//      si el valor es diferente de null se hace una act en la variable y noi una incersion
        if (interIDTask != null) {
            task.setIdTask(interIDTask);
        }
//      recuperamos los valores proporcionados por el usuario
        task.setNameTask(nameTaskText.getText());
        task.setResponsible(responsibleTaskText.getText());
        task.setStatus(statusTaskText.getText());
    }

    private void refreshForm() {
//        nos aseguramos que el id se refresque a null para no tener problemas
        interIDTask = null;
//        limpiamos cada uno de los datos proporcionados por el usuario despues de que se guarde o actalice la tarea
        nameTaskText.clear();
        responsibleTaskText.clear();
        statusTaskText.clear();
    }

    public void updateTaskButton() {
        if (interIDTask == null) {
            showMessage("Danger", "Selected Task");
        }
        if (nameTaskText.getText().isEmpty()) {
            showMessage("Validate Error", "Provide a Task");
            nameTaskText.requestFocus();
            return;
        }
        Task task = new Task();
        dataForm(task);
        taskService.saveTask(task);
        showMessage("Information", "Update Task");
        refreshForm();
        listTask();


    }

    public void deleteTaskButton(){
        if (interIDTask == null) {
            showMessage("Danger", "Selected Task");
        }
        if (nameTaskText.getText().isEmpty()) {
            showMessage("Validate Error", "Provide a Task");
            nameTaskText.requestFocus();
            return;
        }
        Task task = new Task();
        dataForm(task);
        taskService.deleteTask(task);
        showMessage("Information","Delete Task");
        refreshForm();
        listTask();
    }

    public void refreshTaskButton(){
        refreshForm();
    }

}
