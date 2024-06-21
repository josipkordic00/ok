package ba.sum.fpmoz.automobilipuj.repositories;

import ba.sum.fpmoz.automobilipuj.models.automobil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutomobilRepository extends JpaRepository<automobil, Integer> {

}


