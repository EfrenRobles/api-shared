package api.shared.infrastructure.gateway.iam;

public class StatusResponse {

    private String status;
    private DataResponse data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataResponse getData() {
        return data;
    }

    public void setData(DataResponse data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StatusResponse ["
            + "status=" + status
            + ", data=" + data
            + "]";
    }
}
