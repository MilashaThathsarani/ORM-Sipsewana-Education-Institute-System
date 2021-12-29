package dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StudentDTO {
    @Id
    private String studentId;
    private String studentName;
    private String address;
    private String birthday;
    private int age;
    private String gender;
    private String phoneNumber;
    private String education;

    public StudentDTO() {
    }

    public StudentDTO(String studentId, String studentName, String address, String birthday, int age, String gender, String phoneNumber, String education) {
        this.setStudentId(studentId);
        this.setStudentName(studentName);
        this.setAddress(address);
        this.setBirthday(birthday);
        this.setAge(age);
        this.setGender(gender);
        this.setPhoneNumber(phoneNumber);
        this.setEducation(education);
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", education='" + education + '\'' +
                '}';
    }
}
