package repositories.UtilizatorRepository;

import domain.entities.Utilizator;
import repositories.CrudRepository;

public interface UtilizatorRepository extends CrudRepository<Utilizator, String> {
    Utilizator findOne(String uuid);
}
