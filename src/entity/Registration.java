package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Registration implements SuperEntity{
    @Id
    private String registerId;
    private String registerDate;
    private String time;
    private String payment;

    @ManyToOne
    private Student student;


    public Registration() {
    }

    public Registration(String registerId, String registerDate, String time, String payment, Student student) {
        this.setRegisterId(registerId);
        this.setRegisterDate(registerDate);
        this.setTime(time);
        this.setPayment(payment);
        this.setStudent(student);
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "registerId='" + registerId + '\'' +
                ", registerDate='" + registerDate + '\'' +
                ", time='" + time + '\'' +
                ", payment=" + payment +
                ", student=" + student +
                '}';
    }
}