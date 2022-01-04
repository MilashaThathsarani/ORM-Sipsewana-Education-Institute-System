package view.tm;

public class RegisterDetailTM {
    private String registerId;
    private String programId;
    private String duration;
    private double fee;
    private String total;

    public RegisterDetailTM() {
    }

    public RegisterDetailTM(String registerId, String programId, String duration, double fee, String total) {
        this.setRegisterId(registerId);
        this.setProgramId(programId);
        this.setDuration(duration);
        this.setFee(fee);
        this.setTotal(total);
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
