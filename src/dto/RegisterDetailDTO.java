package dto;

public class RegisterDetailDTO {
    private String registerId;
    private String studentId;
    private String programId;

    public RegisterDetailDTO() {
    }

    public RegisterDetailDTO(String registerId, String studentId, String programId) {
        this.registerId = registerId;
        this.studentId = studentId;
        this.programId = programId;
    }

    public RegisterDetailDTO(String studentId, String programId) {
        this.studentId = studentId;
        this.programId = programId;
    }

    @Override
    public String toString() {
        return "RegisterDetailDTO{" +
                "registerId='" + registerId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", programId='" + programId + '\'' +
                '}';
    }
}
