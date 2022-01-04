package view.tm;

public class RegisterTM {
    private String registerId;
    private String studentId;
    private String studentName;
    private String address;
    private int age;
    private String phoneNumber;

    public RegisterTM() {
    }

    public RegisterTM(String registerId, String studentId, String studentName, String address, int age, String phoneNumber) {
        this.setRegisterId(registerId);
        this.setStudentId(studentId);
        this.setStudentName(studentName);
        this.setAddress(address);
        this.setAge(age);
        this.setPhoneNumber(phoneNumber);
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
