package cl.iplacex.proyecto_discografia.discos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DiscoService {

    @Autowired
    private DiscoRepository discoRepository;

    public List<Disco> darDiscos() {
        return discoRepository.findAll();
    }

    public Optional<Disco> darDisco(String id) {
        return discoRepository.findById(id);
    }

    public Disco insertarDisco(Disco disco) {
        return discoRepository.save(disco);
    }

    public Disco actualizarDisco(String id, Disco disco) {
        disco.id = id;
        return discoRepository.save(disco);
    }

    public void eliminarDisco(String id) {
        discoRepository.deleteById(id);
    }
}