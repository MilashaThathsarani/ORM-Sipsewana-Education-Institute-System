package view.tm;

public class RegisterTM {
    private String registerId;
    private String studentId;
    private String studentName;
    private String address;
    private String payment;

    public RegisterTM(String registerId, String studentId, String studentName, String address, String payment) {
        this.setRegisterId(registerId);
        this.setStudentId(studentId);
        this.setStudentName(studentName);
        this.setAddress(address);
        this.setPayment(payment);
    }

    public RegisterTM() {
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
