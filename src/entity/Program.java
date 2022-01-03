package entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Program implements SuperEntity {
    @Id
    private String programId;
    private String programName;
    private String duration;
    private double fee;

    @ManyToMany(mappedBy = "programId" , fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<RegisterDetail> pid;

    public Program() {
    }

    public Program(String programId, String programName, String duration, double fee) {
        this.setProgramId(programId);
        this.setProgramName(programName);
        this.setDuration(duration);
        this.setFee(fee);
    }

    public Program(String programId, String programName, String duration, double fee, List<RegisterDetail> pid) {
        this.setProgramId(programId);
        this.setProgramName(programName);
        this.setDuration(duration);
        this.setFee(fee);
        this.setPid(pid);
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

    public List<RegisterDetail> getPid() {
        return pid;
    }

    public void setPid(List<RegisterDetail> pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programId='" + programId + '\'' +
                ", programName='" + programName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                ", pid=" + pid +
                '}';
    }
}
