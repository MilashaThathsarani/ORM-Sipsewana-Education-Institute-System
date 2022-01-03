package dto;

public class RegisterDetailDTO {
    private Long registerId;
    private String studentId;
    private String programId;

    public RegisterDetailDTO() {
    }

    public RegisterDetailDTO(Long registerId, String studentId, String programId) {
        this.setRegisterId(registerId);
        this.setStudentId(studentId);
        this.setProgramId(programId);
    }

    public RegisterDetailDTO(String programId, String studentId) {
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
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

    @Override
    public String toString() {
        return "RegisterDetailDTO{" +
                "registerId=" + registerId +
                ", studentId='" + studentId + '\'' +
                ", programId='" + programId + '\'' +
                '}';
    }
}
