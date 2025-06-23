
import java.util.concurrent.*;

// Subsistema: Navegación
class SistemaNavegacion implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Navegación: trayectoria corregida con éxito.";
    }
}

// Subsistema: Soporte Vital
class SistemaSoporteVital implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(1200);
        return "Soporte vital: presión y oxígeno dentro de parámetros normales.";
    }
}

// Subsistema: Control Térmico
class SistemaControlTermico implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(1500);
        return "Control térmico: temperatura estable (22°C).";
    }
}

// Subsistema: Comunicaciones
class SistemaComunicaciones implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(800);
        return "Comunicaciones: enlace con estación terrestre establecido.";
    }
}

public class SimulacionMisionEspacial {

    public static void main(String[] args) throws Exception {
        System.out.println("Simulación de misión espacial iniciada...");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Future<String> nav = executor.submit(new SistemaNavegacion());
        Future<String> vida = executor.submit(new SistemaSoporteVital());
        Future<String> termico = executor.submit(new SistemaControlTermico());
        Future<String> comm = executor.submit(new SistemaComunicaciones());

        // Mostrar resultados (orden puede variar por el tiempo de espera de cada uno)
        System.out.println(comm.get());
        System.out.println(vida.get());
        System.out.println(termico.get());
        System.out.println(nav.get());

        executor.shutdown();
        System.out.println("Todos los sistemas reportan estado operativo.");
    }
}
