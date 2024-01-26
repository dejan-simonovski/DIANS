package mk.com.kinmkd.kinmkd.repository;

import mk.com.kinmkd.kinmkd.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import mk.com.kinmkd.kinmkd.model.Location;
import java.util.List;


public interface LocationRepository extends JpaRepository<Location, Integer> {
}
