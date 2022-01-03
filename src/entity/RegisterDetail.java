package entity;

import javax.persistence.*;

public class RegisterDetail implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long registerId;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "programId")
    private Program programId;

    @ManyToOne
    @JoinColumn(name = "sid")
    private Student sid;

    public RegisterDetail() {
    }

    public RegisterDetail(Long registerId, Program programId, Student sid){
        this.registerId = registerId;
        this.programId = programId;
        this.sid = sid;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public Program getProgramId() {
        return programId;
    }

    public void setProgramId(Program programId) {
        this.programId = programId;
    }

    public Student getSid() {
        return sid;
    }

    public void setSid(Student sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "RegisterDetail{" +
                "registerId=" + registerId +
                ", programId=" + programId +
                ", sid=" + sid +
                '}';
    }
}
