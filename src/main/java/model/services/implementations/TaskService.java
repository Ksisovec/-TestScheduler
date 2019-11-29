package model.services.implementations;

import model.Entity.Tasks;
import model.dao.implementations.TaskDAO;
import model.services.GenericService;
import model.services.interfaces.ITaskService;

import java.util.ArrayList;
import java.util.List;

public class TaskService extends GenericService<Tasks, Long> implements ITaskService {

    private TaskDAO tasksDAO = new TaskDAO();

    public TaskService() {
        tasksDAO.setClazz(Tasks.class);
        setDAO(tasksDAO);
    }

    public List<Tasks> findTasksByWorkerId(Long workerId){
        return tasksDAO.findTasksByWorkerId(workerId);
    }

}
