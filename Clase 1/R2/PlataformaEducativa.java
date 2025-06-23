
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// Clase abstracta
abstract class MaterialCurso {
    protected String titulo;
    protected String autor;

    public MaterialCurso(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public abstract void mostrarDetalle();

    public String getAutor() {
        return autor;
    }
}

// Subclase: Video
class Video extends MaterialCurso {
    private int duracion; // en minutos

    public Video(String titulo, String autor, int duracion) {
        super(titulo, autor);
        this.duracion = duracion;
    }

    public int getDuracion() {
        return duracion;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("Video: " + titulo + " - Autor: " + autor + " - Duración: " + duracion + " min");
    }
}

// Subclase: Articulo
class Articulo extends MaterialCurso {
    private int palabras;

    public Articulo(String titulo, String autor, int palabras) {
        super(titulo, autor);
        this.palabras = palabras;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("Artículo: " + titulo + " - Autor: " + autor + " - Palabras: " + palabras);
    }
}

// Subclase: Ejercicio
class Ejercicio extends MaterialCurso {
    private boolean revisado;

    public Ejercicio(String titulo, String autor) {
        super(titulo, autor);
        this.revisado = false;
    }

    public void marcarRevisado() {
        this.revisado = true;
        System.out.println("Ejercicio '" + titulo + "' marcado como revisado.");
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("Ejercicio: " + titulo + " - Autor: " + autor + " - Revisado: " + revisado);
    }
}

public class PlataformaEducativa {

    // Mostrar todos los materiales
    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        for (MaterialCurso material : lista) {
            material.mostrarDetalle();
        }
        System.out.println();
    }

    // Contar duración total de videos
    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        for (Video video : lista) {
            total += video.getDuracion();
        }
        System.out.println("Duración total de videos: " + total + " minutos\n");
    }

    // Marcar ejercicios como revisados
    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        for (Object obj : lista) {
            if (obj instanceof Ejercicio) {
                ((Ejercicio) obj).marcarRevisado();
            }
        }
        System.out.println();
    }

    // Filtrar materiales por autor
    public static void filtrarPorAutor(List<? extends MaterialCurso> lista, Predicate<MaterialCurso> filtro) {
        System.out.println("Filtrando materiales por autor:");
        for (MaterialCurso material : lista) {
            if (filtro.test(material)) {
                material.mostrarDetalle();
            }
        }
    }

    public static void main(String[] args) {
        List<MaterialCurso> materiales = new ArrayList<>();
        materiales.add(new Video("Introducción a Java", "Mario", 15));
        materiales.add(new Video("POO en Java", "Carlos", 20));
        materiales.add(new Articulo("Historia de Java", "Ana", 1200));
        materiales.add(new Articulo("Tipos de datos", "Luis", 800));
        materiales.add(new Ejercicio("Variables y tipos", "Luis"));
        materiales.add(new Ejercicio("Condicionales", "Mario"));

        System.out.println("Materiales del curso:");
        mostrarMateriales(materiales);

        // Filtrar videos y contar duración
        List<Video> videos = new ArrayList<>();
        for (MaterialCurso material : materiales) {
            if (material instanceof Video) {
                videos.add((Video) material);
            }
        }
        contarDuracionVideos(videos);

        // Marcar ejercicios como revisados
        marcarEjerciciosRevisados(materiales);

        // Filtrar por autor "Mario"
        filtrarPorAutor(materiales, m -> m.getAutor().equals("Mario"));
    }
}
