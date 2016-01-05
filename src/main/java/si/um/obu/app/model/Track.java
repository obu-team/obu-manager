package si.um.obu.app.model;

import java.util.List;

public class Track {

    private String id;
    private long distance;
    private long duration;
    private List<TrackPoint> trackPoints;

    public Track() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public List<TrackPoint> getTrackPoints() {
        return trackPoints;
    }

    public void setTrackPoints(List<TrackPoint> trackPoints) {
        this.trackPoints = trackPoints;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id='" + id + '\'' +
                ", distance=" + distance +
                ", duration=" + duration +
                ", trackPoints=" + trackPoints +
                '}';
    }
}
