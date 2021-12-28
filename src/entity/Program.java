package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Program {
    @Id
    private String programId;
    private String programName;
    private String duration;
    private double fee;

    @ManyToMany(mappedBy = "programList")
    private List<Student> studentList = new ArrayList<>();

    public Program() {
    }

    public Program(String programId, String programName, String duration, double fee) {
        this.setProgramId(programId);
        this.setProgramName(programName);
        this.setDuration(duration);
        this.setFee(fee);
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programId='" + programId + '\'' +
                ", programName='" + programName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                '}';
    }
}
