package cl.iplacex.proyecto_discografia.artistas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "artistas")
public class Artista {
    @Id
    public String id;
    public String nombre;
    public String genero;
    public int integrantes;
public int anioFormacion;
    public boolean estaActivo;
    public Artista() {
    }

    public Artista(String nombre, String genero, int integrantes) {
        this.nombre = nombre;
        this.genero = genero;
        this.integrantes = integrantes;
    }
}