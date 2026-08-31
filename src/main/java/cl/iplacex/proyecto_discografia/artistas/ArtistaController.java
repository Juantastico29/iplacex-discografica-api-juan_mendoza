package cl.iplacex.proyecto_discografia.artistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ArtistaController {

    @Autowired
    private ArtistaRepository artistaRepository;

    @PostMapping("/artista")
    public ResponseEntity<Artista> insertarArtista(@RequestBody Artista artista) {
        Artista nuevoArtista = artistaRepository.save(artista);
        return new ResponseEntity<>(nuevoArtista, HttpStatus.CREATED);
    }

    @GetMapping("/artistas")
    public ResponseEntity<List<Artista>> obtenerArtistas() {
        List<Artista> artistas = artistaRepository.findAll();
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }

    @GetMapping("/artista/{id}")
    public ResponseEntity<Artista> obtenerArtistaPorId(@PathVariable String id) {
        Optional<Artista> artista = artistaRepository.findById(id);
        if (artista.isPresent()) {
            return new ResponseEntity<>(artista.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/artista/{id}")
    public ResponseEntity<Artista> actualizarArtista(@PathVariable String id, @RequestBody Artista artista) {
        Optional<Artista> artistaActual = artistaRepository.findById(id);
        if (artistaActual.isPresent()) {
            Artista artistaExistente = artistaActual.get();
            artistaExistente.nombre = artista.nombre;
            artistaExistente.genero = artista.genero;
            artistaExistente.integrantes = artista.integrantes;
            artistaRepository.save(artistaExistente);
            return new ResponseEntity<>(artistaExistente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/artista/{id}")
    public ResponseEntity<Void> eliminarArtista(@PathVariable String id) {
        if (artistaRepository.existsById(id)) {
            artistaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}