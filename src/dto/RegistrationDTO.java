package dto;

import java.util.List;

public class RegistrationDTO {
    private String registerId;
    private String studentId;
    private String programId;
    private String registerDate;
    private String time;
    private String payment;
    private List<RegisterDetailDTO> registerDetail;

    public RegistrationDTO(String registerId, String studentId, String programId, String registerDate, String time, String payment, List<RegisterDetailDTO> registerDetail) {
        this.setRegisterId(registerId);
        this.setStudentId(studentId);
        this.setProgramId(programId);
        this.setRegisterDate(registerDate);
        this.setTime(time);
        this.setPayment(payment);
        this.setRegisterDetail(registerDetail);
    }

    public RegistrationDTO() {
    }

    public RegistrationDTO(String registerId, String studentId, String pid, String registerDate, String time, String payment) {
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<RegisterDetailDTO> getRegisterDetail() {
        return registerDetail;
    }

    public void setRegisterDetail(List<RegisterDetailDTO> registerDetail) {
        this.registerDetail = registerDetail;
    }
}
