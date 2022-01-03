package dto;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDTO {
    private String registerId;
    private String studentId;
    private String programId;
    private String registerDate;
    private String time;
    private double payment;
    private List<RegisterDetailDTO> registerDetail;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String registerId, String studentId, String programId, String registerDate, String time, double payment, List<RegisterDetailDTO> registerDetail) {
        this.setRegisterId(registerId);
        this.setStudentId(studentId);
        this.setProgramId(programId);
        this.setRegisterDate(registerDate);
        this.setTime(time);
        this.setPayment(payment);
        this.setRegisterDetail(registerDetail);
    }

    public RegistrationDTO(String registerId, String studentId, String programId, String registerDate, String time, String payment, ArrayList<RegisterDetailDTO> registerDetailDTOS) {
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
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

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public List<RegisterDetailDTO> getRegisterDetail() {
        return registerDetail;
    }

    public void setRegisterDetail(List<RegisterDetailDTO> registerDetail) {
        this.registerDetail = registerDetail;
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "registerId='" + registerId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", programId='" + programId + '\'' +
                ", registerDate='" + registerDate + '\'' +
                ", time='" + time + '\'' +
                ", payment=" + payment +
                ", registerDetail=" + registerDetail +
                '}';
    }
}
