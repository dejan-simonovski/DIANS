package mk.com.kinmkd.kinmkd.Initializer;

import jakarta.annotation.PostConstruct;
import mk.com.kinmkd.kinmkd.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class DataInitializer {

    private final LocationService locationService;

    @Autowired
    public DataInitializer(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostConstruct
    public void init() {
        try {
            String jsonFilePath = "src\\main\\java\\mk/com\\kinmkd\\kinmkd\\Initializer\\locations.json";
            locationService.insertDataFromJsonFile(jsonFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
