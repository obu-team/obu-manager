package si.um.obu.app.model;

public class TrackPoint {

    private GeoLocation location;
    private long timestamp;

    public TrackPoint() {
    }

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TrackPoint{" +
                "location=" + location +
                ", timestamp=" + timestamp +
                '}';
    }
}
