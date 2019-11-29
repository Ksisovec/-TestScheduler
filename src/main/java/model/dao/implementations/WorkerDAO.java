package model.dao.implementations;

import model.Entity.Tasks;
import model.Entity.Worker;
import model.dao.GenericDAO;
import model.dao.interfaces.IWorkerDAO;
import model.utils.HibernateSessionFactoryUtil;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorkerDAO extends GenericDAO<Worker, Long> implements IWorkerDAO {

    public WorkerDAO(){
        setClazz(Worker.class);
    }

    @Override
    public Worker findWorkerByName(String name) {
        Worker worker = null;
        String sql = "SELECT w FROM Worker w WHERE w.name = :name";
        Query query = HibernateSessionFactoryUtil.getSession().createQuery(sql).setParameter("name", name);
        worker = findOne(query);
        return worker;
    }

    @Override
    public List<Tasks> findWorkerTasksByWorker(Worker worker) throws NullPointerException {
//        List<Long> tasksId = findById(worker.getId()).get().getTasks().
//                stream().map(t -> t.getId()).collect(Collectors.toList());
//
//        String sql = "SELECT tasks_id FROM W WHERE w.name = :name";
//        Query query = HibernateSessionFactoryUtil.getSession().createQuery(sql).setParameter("name", name);
//
//        List<Tasks> tasksList = new ArrayList<>();
//        TaskDAO taskDAO = new TaskDAO();
//        taskDAO.findTasksByWorkerId();
//        for (Long id : tasksId) {
//            tasksList.add(taskDAO.findById(id).orElseThrow(() -> new NullPointerException()));
//        }
//        return tasksList;
        return null;
    }

}
