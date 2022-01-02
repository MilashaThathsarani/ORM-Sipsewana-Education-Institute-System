package view.tm;

public class CartTM {
    private String registerId;
    private String StudentId;
    private String programId;
    private String studentName;
    private String programName;
    private double total;

    public CartTM() {
    }

    public CartTM(String registerId, String studentId, String programId, String studentName, String programName, double total) {
        this.setRegisterId(registerId);
        setStudentId(studentId);
        this.setProgramId(programId);
        this.setStudentName(studentName);
        this.setProgramName(programName);
        this.setTotal(total);
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CartTM{" +
                "registerId='" + registerId + '\'' +
                ", StudentId='" + StudentId + '\'' +
                ", programId='" + programId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", programName='" + programName + '\'' +
                ", total=" + total +
                '}';
    }
}
