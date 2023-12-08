package mk.com.kinmkd.kinmkd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mk.com.kinmkd.kinmkd.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
}
