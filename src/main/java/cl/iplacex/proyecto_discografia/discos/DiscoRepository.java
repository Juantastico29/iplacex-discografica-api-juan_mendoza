package cl.iplacex.proyecto_discografia.discos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DiscoRepository extends MongoRepository<Disco, String> {
    List<Disco> findByIdArtista(String idArtista);
}