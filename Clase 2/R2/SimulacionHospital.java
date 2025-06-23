
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

class RecursoMedico {
    private final String nombre;
    private final ReentrantLock lock = new ReentrantLock();

    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }

    public void usar(String profesional) {
        lock.lock();
        try {
            System.out.println("Iniciando acceso a " + nombre + "...");
            System.out.println(profesional + " ha ingresado a " + nombre);
            Thread.sleep((long) (Math.random() * 2000 + 1000));
            System.out.println("OK" + profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}

public class SimulacionHospital {
    public static void main(String[] args) {
        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        Runnable tarea1 = () -> salaCirugia.usar("Dra. Sánchez");
        Runnable tarea2 = () -> salaCirugia.usar("Dr. Gómez");
        Runnable tarea3 = () -> salaCirugia.usar("Dra. Martínez");
        Runnable tarea4 = () -> salaCirugia.usar("Dr. Pérez");
        Runnable tarea5 = () -> salaCirugia.usar("Dr. López");

        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.submit(tarea1);
        executor.submit(tarea2);
        executor.submit(tarea3);
        executor.submit(tarea4);
        executor.submit(tarea5);

        executor.shutdown();
    }
}
