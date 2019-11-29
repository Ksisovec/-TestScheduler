package model;

import model.Entity.Tasks;
import model.Entity.Worker;
import model.services.implementations.TaskService;
import model.services.implementations.WorkerService;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {


    public static void main(final String[] args) throws Exception {
        WorkerService workerService = new WorkerService();
        TaskService tasksService = new TaskService();

        Worker workerA = new Worker("Amieloo");
        Worker workerB = new Worker("Bobini");

        ZonedDateTime zdt1 = ZonedDateTime.of(2019, 2, 10, 14, 0, 0, 0,
                ZoneId.of("Europe/Moscow"));
        Instant instant = zdt1.toInstant() ;
        Timestamp startDate = Timestamp.from ( instant ) ;

        ZonedDateTime zdt2 = ZonedDateTime.of(2019, 3, 2, 14, 0, 0, 0,
                ZoneId.of("Europe/Moscow"));
        Instant instant2 = zdt2.toInstant() ;
        Timestamp endDate = Timestamp.from ( instant2 ) ;


        ZonedDateTime zdt3 = ZonedDateTime.of(2019, 2, 19, 14, 0, 0, 0,
                ZoneId.of("Europe/Moscow"));
        Instant instant3 = zdt2.toInstant() ;
        Timestamp doneDate = Timestamp.from ( instant2 ) ;

        Tasks tasks1 = new Tasks("Task1", startDate, endDate, doneDate, true);
        Tasks tasks2 = new Tasks("Task2", startDate, endDate, doneDate, true);
        Tasks tasks3 = new Tasks("Task3", startDate, endDate, doneDate, true);
        Tasks tasks4 = new Tasks("Task4", startDate, endDate, doneDate, true);

        workerA.addTask(tasks1);
        tasks1.setWorker(workerA);
        workerA.addTask(tasks2);
        tasks2.setWorker(workerA);
        workerB.addTask(tasks3);
        tasks3.setWorker(workerB);
        workerB.addTask(tasks4);
        tasks4.setWorker(workerB);

        for (Worker worker : workerService.findAll()) {
            workerService.delete(worker);
        }
//        workerService.save(workerA);
//        workerService.save(workerB);

    }
}