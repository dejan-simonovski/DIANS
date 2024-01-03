package mk.com.kinmkd.kinmkd;

import mk.com.kinmkd.kinmkd.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class KinMkdApplication {

    public static void main(String[] args) {
        SpringApplication.run(KinMkdApplication.class, args);
    }
}
