package model.Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "tasks", schema = "new_schema", catalog = "new_database")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "start_Date", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_Date", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "done_Date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date doneDate;

    @Column(name = "isdone", nullable = false)
    private Boolean isdone;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    public Tasks() {}

    public Tasks(String name, Timestamp startDate, Timestamp endDate, Timestamp doneDate, Boolean isdone) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.doneDate = doneDate;
        this.isdone = isdone;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Timestamp doneDate) {
        this.doneDate = doneDate;
    }

    public Boolean getIsdone() {
        return isdone;
    }

    public void setIsdone(Boolean isdone) {
        this.isdone = isdone;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tasks that = (Tasks) o;

        if (id != that.id) return false;
        if (!worker.equals(that.worker)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (doneDate != null ? !doneDate.equals(that.doneDate) : that.doneDate != null) return false;
        if (isdone != null ? !isdone.equals(that.isdone) : that.isdone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (doneDate != null ? doneDate.hashCode() : 0);
        result = 31 * result + (isdone != null ? isdone.hashCode() : 0);
        result = 31 * result + worker.hashCode();
        return result;
    }

    @Override
    public String toString(){
        String task = "\nname " + name + "\n start date " + startDate.toString()
                + "\n end date "+ endDate.toString()
                + "\n done date "+doneDate.toString()
                + "\n is done "+  isdone.toString() + "\n";
        return task;
    }
}
