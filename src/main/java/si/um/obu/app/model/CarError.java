package si.um.obu.app.model;

public class CarError {

    protected String code;
    protected long timestamp;
    protected CarErrorType type;

    public CarError() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public CarErrorType getType() {
        return type;
    }

    public void setType(CarErrorType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CarError{" +
                "code='" + code + '\'' +
                ", timestamp=" + timestamp +
                ", type=" + type +
                '}';
    }
}
