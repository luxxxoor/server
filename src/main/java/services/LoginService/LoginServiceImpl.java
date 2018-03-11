package services.LoginService;

import domain.Rol;
import domain.entities.Utilizator;
import repositories.UtilizatorRepository.UtilizatorRepository;

import java.util.Map;
import java.util.UUID;

public class LoginServiceImpl implements LoginService {

    private UtilizatorRepository utilizatorRepository;

    public LoginServiceImpl(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }

    @Override
    public void creteUtilizatorPentruDonator(Map<String, String> informatiiUtilizator) {
        //prima data vom creea un user cu rolul DONATOR si dupa aceea un donator asociat acestui user
        final String username = informatiiUtilizator.get("username");
        final String parola = informatiiUtilizator.get("parola");
        UUID uuid;
        do {
            uuid = UUID.randomUUID();
        } while (utilizatorRepository.findOne(uuid.toString()) != null);
        utilizatorRepository.save(new Utilizator(uuid.toString(), Rol.DONATOR, username, parola));
    }
}
