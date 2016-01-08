package si.um.obu.app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import si.um.obu.app.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class OBUService {

    private static final String OBU_API_URL = "http://obu.grega.xyz/api/";

    private RestTemplate restTemplate = new RestTemplate();

    public boolean isOBUIdValid(Token token) {
        Set<String> ids = restTemplate.getForObject(OBU_API_URL + "ids", Set.class);
        if(ids.contains(token.getValue())) {
            return true;
        }
        return false;
    }

    public GeoLocation getOBULocation(String OBUid) {
        return restTemplate.getForObject(OBU_API_URL + OBUid + "/location", GeoLocation.class);
    }

    public HashMap<CarParameter, Float> getCarParamterValues(String OBUId) {
        HashMap<CarParameter, Float> params = restTemplate.getForObject(OBU_API_URL + OBUId + "/params", HashMap.class);
        return params;
    }

    public List<Track> getCarDriveHistory(String OBUId) {
        GetCarDriveHistoryResponse response = restTemplate.getForObject(OBU_API_URL + OBUId + "/driveHistory",
                GetCarDriveHistoryResponse.class);
        return response.getTracks();
    }

    public GeoLocation getCarDestination(String OBUId) {
        return restTemplate.getForObject(OBU_API_URL + OBUId + "/currentTrackEndLocation", GeoLocation.class);
    }

    public List<CarError> getCarErrors(String OBUId) {
        return restTemplate.getForObject(OBU_API_URL + OBUId + "/carErrors", List.class);
    }

}
