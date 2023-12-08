package mk.com.kinmkd.kinmkd.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

public interface LocationService {

    void insertDataFromJsonFile(String jsonFilePath) throws IOException;
}
