
import java.util.*;
import java.util.stream.*;

class Encuesta {
    private String paciente;
    private String comentario;
    private int calificacion;

    public Encuesta(String paciente, String comentario, int calificacion) {
        this.paciente = paciente;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public Optional<String> getComentario() {
        return Optional.ofNullable(comentario);
    }
}

class Sucursal {
    private String nombre;
    private List<Encuesta> encuestas;

    public Sucursal(String nombre, List<Encuesta> encuestas) {
        this.nombre = nombre;
        this.encuestas = encuestas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Encuesta> getEncuestas() {
        return encuestas;
    }
}

public class ProcesadorEncuestas {
    public static void main(String[] args) {
        List<Sucursal> sucursales = new ArrayList<>();

        List<Encuesta> encuestasCentro = Arrays.asList(
            new Encuesta("Carlos", "El tiempo de espera fue largo", 2),
            new Encuesta("Ana", null, 2),
            new Encuesta("Marta", "Servicio excelente", 5)
        );

        List<Encuesta> encuestasNorte = Arrays.asList(
            new Encuesta("Jorge", "La atención fue buena, pero la limpieza puede mejorar", 3),
            new Encuesta("Luis", null, 1),
            new Encuesta("Sofía", "Todo bien", 4)
        );

        sucursales.add(new Sucursal("Centro", encuestasCentro));
        sucursales.add(new Sucursal("Norte", encuestasNorte));

        sucursales.stream()
                .flatMap(sucursal -> sucursal.getEncuestas().stream()
                        .filter(encuesta -> encuesta.getCalificacion() <= 3)
                        .flatMap(encuesta -> encuesta.getComentario().stream()
                                .map(comentario -> "Sucursal " + sucursal.getNombre() + ": Seguimiento a paciente con comentario: "" + comentario + """)))
                .forEach(System.out::println);
    }
}
