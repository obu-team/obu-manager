package si.um.obu.app.model;

import java.util.List;

public class GetCarDriveHistoryResponse {

    private List<Track> tracks;

    public GetCarDriveHistoryResponse() {
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "GetCarDriveHistoryResponse{" +
                "tracks=" + tracks +
                '}';
    }
}
