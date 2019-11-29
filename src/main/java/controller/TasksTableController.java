package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Entity.Tasks;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TasksTableController {

    @FXML
    private TableColumn<Tasks, Boolean> isDone;
    @FXML
    private TableColumn<Tasks, String> name;
    @FXML
    private TableColumn<Tasks, Date> startDate;
    @FXML
    private TableColumn<Tasks, Date> endDate;
    @FXML
    private TableColumn<Tasks, Date> doneDate;
    @FXML
    private TableView<Tasks> tasksTable;

    /**
     * Устанавливаем value factory для полей таблицы
     */
    @FXML
    public void initialize() {
        isDone.setCellValueFactory(new PropertyValueFactory<>("isDone"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        doneDate.setCellValueFactory(new PropertyValueFactory<>("doneDate"));
        tasksTable.getColumns().setAll(isDone, name, startDate, endDate, doneDate);
        //TODO: for debug
        System.out.println("TaskTable init");
        Timestamp date = new Timestamp(System.currentTimeMillis());
        List<Tasks>  tasks = new ArrayList<>();
        Tasks tasks1 = new Tasks("Task1", date, date, date, true);
        tasks.add(tasks1);
        tasksTable.setItems(FXCollections.observableArrayList(tasks));
    }


    /**
     * Заполняем таблицу данными из БД
     * @param tasks список задач
     */
    public void fillTable(List<Tasks> tasks) {
        if(FXCollections.observableArrayList(tasks) == null)
            System.out.println("fx collect == null");
        if(tasksTable == null)
            System.out.println("taskTable = null");
        tasksTable.setItems(FXCollections.observableArrayList(tasks));
    }
}
