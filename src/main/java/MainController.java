
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Entity.Tasks;
import model.Entity.Worker;
import model.services.implementations.TaskService;
import model.services.implementations.WorkerService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class MainController {


    @FXML
    private TableColumn<Tasks, Boolean> isdone;
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

//

    @FXML
    private TableView<Row> planTable;


    @FXML
    AnchorPane planPane;

    @FXML
    ComboBox<String> workerNames;

    @FXML
    Label nameLabel;
    @FXML
    ComboBox<String> monthBox;

    WorkerService workerService;
    TaskService taskService;

    Worker selectedWorker;

    @FXML
    private void initialize() {
        {
            isdone.setCellValueFactory(new PropertyValueFactory<>("isdone"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            doneDate.setCellValueFactory(new PropertyValueFactory<>("doneDate"));
//            tasksTable.getColumns().setAll(done, name, startDate, endDate, doneDate);
            //TODO: for debug
//            System.out.println("TaskTable init");
//            Timestamp date = new Timestamp(System.currentTimeMillis());
//            List<Tasks> tasks = new ArrayList<>();
//            Tasks tasks1 = new Tasks("Task1", date, date, date, true);
//            tasks.add(tasks1);
//            tasksTable.setItems(FXCollections.observableArrayList(tasks));

//            nameTask.setCellValueFactory(new PropertyValueFactory<>("name"));
        }


        workerService = new WorkerService();
        taskService = new TaskService();

        //worker combobox
        workerNames.setItems(FXCollections.observableArrayList(
                workerService.findAll().
                        stream().map(a -> a.getName()).collect(Collectors.toList())));
//        workerNames.getSelectionModel().selectFirst();

        //month ComboBox
        Map<String, Integer> monthMap = new HashMap<>();
        //нет вермери, потом переделаешь!
        monthMap.put("Январь", 12);
        monthMap.put("Февраль", 1);
        monthMap.put("Март", 2);
        monthMap.put("Апрель", 3);
        monthMap.put("Май", 4);
        monthMap.put("Июнь", 5);
        monthMap.put("Июль", 6);
        monthMap.put("Август", 7);
        monthMap.put("Сентябрь", 8);
        monthMap.put("Октябрь", 9);
        monthMap.put("Ноябрь", 10);
        monthMap.put("Декабрь", 11);
        monthBox.setItems(FXCollections.observableArrayList(
                new ArrayList<String>(monthMap.keySet())));
//        monthBox.getSelectionModel().selectFirst();


        nameLabel.setText("Сотрудник: ");
        workerNames.valueProperty().addListener((obs, oldItem, newItem) -> {

            nameLabel.textProperty().unbind();
            if (newItem == null) {
                nameLabel.setText("");
            } else {
                nameLabel.setText(newItem);
            }
            selectedWorker = workerService.findWorkerByName(workerNames.getValue());
            List<Tasks> tasksList = taskService.findTasksByWorkerId(
                    selectedWorker.getId());
            fillTasksTable(tasksList);
            if (monthBox.getSelectionModel().getSelectedItem() != null) {

                fillPlaneTable(getNumDays(monthMap), tasksList);
                createColumns(getNumDays(monthMap));
            }
        });

        monthBox.valueProperty().addListener((obs, oldItem, newItem) -> {

            List<Tasks> tasksList = taskService.findTasksByWorkerId(
                    selectedWorker.getId());
            if (workerNames.getSelectionModel().getSelectedItem() != null) {

                fillPlaneTable(getNumDays(monthMap), tasksList);
                createColumns(getNumDays(monthMap));
            }

        });



    }

    static class Row
    {
        private final List<Cell> list = new ArrayList<>();

        public List<Cell> getCells()
        {
            return list;
        }
    }

    static class Cell
    {
        private final String value;

        public Cell(String value)
        {
            this.value = value;
        }

        @Override
        public String toString()
        {
            return value;
        }
    }

    private Calendar getNumDays(Map<String, Integer> monthMap){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, monthMap.get(monthBox.getValue()));
        return calendar;
    }

    private void createColumns(Calendar calendar) {
        planTable.getColumns().clear();
        for(int day = 0; day < calendar.getActualMaximum(Calendar.DATE) + 1; day++) {
            TableColumn<Row, String> column;
            if( day == 0) {
                column = new TableColumn<>("Задача");
                column.setPrefWidth(120);
            } else {
                column = new TableColumn<>(Integer.toString(day));
                column.setPrefWidth((planTable.getPrefWidth() - 120) / calendar.getActualMaximum(Calendar.DATE));
            }
                column.setCellValueFactory(param -> {
                    int index = param.getTableView().getColumns().indexOf(param.getTableColumn());
                    List<Cell> cells = param.getValue().getCells();
                    return new SimpleStringProperty(cells.size() > index ? cells.get(index).toString() : null);
                });

                column.setCellFactory(c -> {
                    return new TableCell<Row, String>() {
                        @Override
                        protected void updateItem(String s, boolean b) {
                            super.updateItem(s, b);
                            if (s == null || b) {
//                            setText(null);
                                setStyle("");
                            } else {
                            if(!s.equals("true") && !s.equals("false"))
                                setText(s);

                                // Style all dates in March with a different color.
                                if (s.equals("true")) {
                                    setStyle("-fx-background-color: yellow");
                                } else {
                                    setStyle("");
                                }
                            }

                        }

                    };
                });
            planTable.getColumns().add(column);
        }
    }

    public List<Row> makePlaneRowData(Calendar calendar, List<Tasks> tasks)
    {
        List<Row> rows = new ArrayList<>();
        for (int row = 0; row < tasks.size(); row++)
        {
            Row currRow = new Row();
            Tasks task = tasks.get(row);
            System.out.println(task.toString());


            for (int cellDay = 0; cellDay <= calendar.getActualMaximum(Calendar.DATE) ; cellDay++)
            {
                if(cellDay == 0)
                    currRow.getCells().add( new Cell(task.getName()));
                else {
                    if (inRange(cellDay, calendar.get(Calendar.MONTH) + 1, task.getStartDate(), task.getEndDate())) {
                        currRow.getCells().add(new Cell("true"));
                    } else {
                        currRow.getCells().add(new Cell("false"));
                    }
                }
            }
            rows.add(currRow);
        }
        return rows;
    }

    private boolean inRange(int day, int month, Date startDate, Date endDate){

        LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("startLocalDate.getYear()" + startLocalDate.getYear());
        System.out.println("month" + month);
        System.out.println("day" + day);
        LocalDate currentLocalDate = LocalDate.of(startLocalDate.getYear(), month, day);
        Boolean containsToday = ( ! currentLocalDate.isBefore( startLocalDate ) ) && ( currentLocalDate.isBefore( endLocalDate ) ) ;
        return containsToday;
    }

//    private ObservableValue<Boolean> fun(int day) {
//        if(day)
//
//    }
//
//    private boolean inPlane(int day) {
//        List<Tasks> tasksList = taskService.findTasksByWorkerId(
//                workerService.findWorkerByName(workerNames.getValue()).getId());
//
//        return true;
//    }

    public void fillPlaneTable(Calendar calendar, List<Tasks> tasks) {
        List<Row> rows = makePlaneRowData(calendar, tasks);
        planTable.getItems().clear();
        planTable.getItems().addAll(rows);

    }
    /**
     * Заполняем таблицу данными из БД
     *
     * @param tasks список задач
     */
    public void fillTasksTable(List<Tasks> tasks) {
        tasksTable.setItems(FXCollections.observableArrayList(tasks));
    }
}

