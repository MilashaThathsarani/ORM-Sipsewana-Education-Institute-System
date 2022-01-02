package dto;

import java.util.List;

public class RegistrationDTO {
    private String registerId;
    private String studentId;
    private String registerDate;
    private String time;
    private double payment;
    private List<RegisterDetailDTO> programs;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String registerId, String registerDate, String time, double payment) {
        this.setRegisterId(registerId);
        this.setRegisterDate(registerDate);
        this.setTime(time);
        this.setPayment(payment);
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

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "registerId='" + registerId + '\'' +
                ", registerDate='" + registerDate + '\'' +
                ", time='" + time + '\'' +
                ", payment=" + payment +
                '}';
    }
}
