package entity;

import javax.persistence.*;

@Entity
public class RegisterDetail implements SuperEntity{
    @Id
    private String registerId;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "programId")
    private Program programId;

    @ManyToOne
    @JoinColumn(name = "sid")
    private Student sid;

    public RegisterDetail(String registerId, Program programId, Student sid) {
        this.setRegisterId(registerId);
        this.setProgramId(programId);
        this.setSid(sid);
    }

    public RegisterDetail() {
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
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
}
