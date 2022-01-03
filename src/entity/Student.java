package entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student implements SuperEntity {
    @Id
    private String studentId;
    private String studentName;
    private String address;
    private String birthday;
    private int age;
    private String gender;
    private String phoneNumber;
    private String education;

    @OneToMany(mappedBy = "sid")
    private List<RegisterDetail> sid;

    @OneToMany(mappedBy = "student" , fetch = FetchType.EAGER)
    private List<Registration> registration;


    public Student(String studentId, String studentName, String address, String birthday, int age, String gender, String phoneNumber, String education) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.address = address;
        this.birthday = birthday;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.education = education;
    }

    public Student() {
    }

    public Student(String studentId, String studentName, String address, String birthday, int age, String gender, String phoneNumber, String education, List<RegisterDetail> sid, List<Registration> registration) {
        this.setStudentId(studentId);
        this.setStudentName(studentName);
        this.setAddress(address);
        this.setBirthday(birthday);
        this.setAge(age);
        this.setGender(gender);
        this.setPhoneNumber(phoneNumber);
        this.setEducation(education);
        this.setSid(sid);
        this.setRegistration(registration);
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

    public List<RegisterDetail> getSid() {
        return sid;
    }

    public void setSid(List<RegisterDetail> sid) {
        this.sid = sid;
    }

    public List<Registration> getRegistration() {
        return registration;
    }

    public void setRegistration(List<Registration> registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", education='" + education + '\'' +
                ", sid=" + sid +
                ", registration=" + registration +
                '}';
    }
}
