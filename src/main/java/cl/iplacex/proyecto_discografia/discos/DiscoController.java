package cl.iplacex.proyecto_discografia.discos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController {

    @Autowired
    private DiscoRepository discoRepository;

    @PostMapping("/disco")
    public ResponseEntity<Disco> insertarDisco(@RequestBody Disco disco) {
        Disco nuevoDisco = discoRepository.save(disco);
        return new ResponseEntity<>(nuevoDisco, HttpStatus.CREATED);
    }

    @GetMapping("/discos")
    public ResponseEntity<List<Disco>> obtenerDiscos() {
        List<Disco> discos = discoRepository.findAll();
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }

    @GetMapping("/disco/{id}")
    public ResponseEntity<Disco> obtenerDiscoPorId(@PathVariable String id) {
        Optional<Disco> disco = discoRepository.findById(id);
        if (disco.isPresent()) {
            return new ResponseEntity<>(disco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/artista/{id}/discos")
    public ResponseEntity<List<Disco>> obtenerDiscosPorArtista(@PathVariable String id) {
        List<Disco> discos = discoRepository.findByIdArtista(id);
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }

    @PutMapping("/disco/{id}")
    public ResponseEntity<Disco> actualizarDisco(@PathVariable String id, @RequestBody Disco disco) {
        Optional<Disco> discoActual = discoRepository.findById(id);
        if (discoActual.isPresent()) {
            Disco discoExistente = discoActual.get();
            discoExistente.idArtista = disco.idArtista;
            discoExistente.nombre = disco.nombre;
            discoExistente.anioLanzamiento = disco.anioLanzamiento;
            discoExistente.canciones = disco.canciones;
            discoRepository.save(discoExistente);
            return new ResponseEntity<>(discoExistente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/disco/{id}")
    public ResponseEntity<Void> eliminarDisco(@PathVariable String id) {
        if (discoRepository.existsById(id)) {
            discoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}