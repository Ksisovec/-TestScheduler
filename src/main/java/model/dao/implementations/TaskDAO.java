package model.dao.implementations;

import model.Entity.Tasks;
import model.dao.GenericDAO;
import model.dao.interfaces.ITaskDAO;
import model.utils.HibernateSessionFactoryUtil;
import org.hibernate.query.Query;

import java.util.List;


public class TaskDAO extends GenericDAO<Tasks, Long> implements ITaskDAO {
    public TaskDAO(){
        setClazz(Tasks.class);
    }

    public List<Tasks> findTasksByWorkerId(Long workerId){
        String sql = "SELECT t FROM Tasks t WHERE t.worker.id = :workerId";
        Query query = HibernateSessionFactoryUtil.getSession().createQuery(sql).setParameter("workerId", workerId);
        return findMany(query);
    }
}
