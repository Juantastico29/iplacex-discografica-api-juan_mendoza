package cl.iplacex.proyecto_discografia.artistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    public List<Artista> darArtistas() {
        return artistaRepository.findAll();
    }

    public Optional<Artista> darArtista(String id) {
        return artistaRepository.findById(id);
    }

    public Artista insertarArtista(Artista artista) {
        return artistaRepository.save(artista);
    }

    public Artista actualizarArtista(String id, Artista artista) {
        artista.id = id;
        return artistaRepository.save(artista);
    }

    public void eliminarArtista(String id) {
        artistaRepository.deleteById(id);
    }
}