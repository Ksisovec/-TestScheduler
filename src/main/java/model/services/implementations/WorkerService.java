package model.services.implementations;

import model.Entity.Tasks;
import model.Entity.Worker;
import model.dao.GenericDAO;
import model.dao.implementations.WorkerDAO;
import model.services.GenericService;
import model.services.interfaces.IWorkerService;
import org.hibernate.query.Query;

import java.util.List;

public class WorkerService extends GenericService<Worker, Long> implements IWorkerService {

    private WorkerDAO workerDAO = new WorkerDAO();

    public WorkerService() {
        workerDAO.setClazz(Worker.class);
        setDAO(workerDAO);
    }

    public Worker findWorkerByName(String name) {
        return workerDAO.findWorkerByName(name);
    }

    public List<Tasks> findAllWorkerTasks(Worker worker) throws NullPointerException{
//        return workerDAO.findWorkerTasksByWorker(worker);
        return null;
    }

}
