package model.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "worker", schema = "new_schema", catalog = "new_database")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @OneToMany(mappedBy = "worker",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<Tasks> tasks;

    public Worker(){
        tasks = new ArrayList<Tasks>();
    }

    public Worker(String name){
        this.name = name;
        tasks = new ArrayList<Tasks>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }

    public List<Tasks> getTasks(){
        return tasks;
    }

    public void addTask(Tasks task) {
        task.setWorker(this);
        tasks.add(task);
    }

    public void removeTask(Tasks task) {
        tasks.remove(task);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Worker that = (Worker) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
