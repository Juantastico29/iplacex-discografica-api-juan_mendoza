package cl.iplacex.proyecto_discografia.artistas;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends MongoRepository<Artista, String> {
}