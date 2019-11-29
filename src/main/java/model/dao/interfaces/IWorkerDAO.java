package model.dao.interfaces;

import model.Entity.Tasks;
import model.Entity.Worker;
import model.dao.DAO;

import java.util.List;

public interface IWorkerDAO extends DAO<Worker, Long> {

    Worker findWorkerByName(String name);

    List<Tasks> findWorkerTasksByWorker(Worker worker) throws Exception;

}
