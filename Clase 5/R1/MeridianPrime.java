
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MeridianPrime {

    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Inicializando sistemas de Meridian Prime...");

        Flux<Integer> trafico = Flux.interval(Duration.ofMillis(500))
                .map(i -> random.nextInt(101))
                .onBackpressureBuffer()
                .filter(congestion -> congestion > 70)
                .map(congestion -> "Alerta: Congestión del " + congestion + "% en avenida principal")
                .subscribeOn(Schedulers.parallel());

        Flux<Double> contaminacion = Flux.interval(Duration.ofMillis(600))
                .map(i -> 30 + random.nextDouble() * 50)
                .filter(nivel -> nivel > 50)
                .map(nivel -> "Alerta: Contaminación alta (PM2.5: " + String.format("%.1f", nivel) + " ug/m3)")
                .subscribeOn(Schedulers.parallel());

        Flux<String> accidentes = Flux.interval(Duration.ofMillis(800))
                .map(i -> {
                    String[] prioridades = {"Baja", "Media", "Alta"};
                    return prioridades[random.nextInt(3)];
                })
                .filter(prioridad -> prioridad.equals("Alta"))
                .map(prioridad -> "Emergencia vial: Accidente con prioridad " + prioridad)
                .subscribeOn(Schedulers.parallel());

        Flux<Integer> trenes = Flux.interval(Duration.ofMillis(700))
                .map(i -> random.nextInt(11))
                .filter(retraso -> retraso > 5)
                .map(retraso -> "Tren maglev con retraso crítico: " + retraso + " minutos")
                .subscribeOn(Schedulers.parallel());

        AtomicInteger rojoCount = new AtomicInteger(0);
        Flux<String> semaforos = Flux.interval(Duration.ofMillis(400))
                .map(i -> {
                    String[] estados = {"Verde", "Amarillo", "Rojo"};
                    return estados[random.nextInt(estados.length)];
                })
                .doOnNext(estado -> {
                    if (estado.equals("Rojo")) rojoCount.incrementAndGet();
                    else rojoCount.set(0);
                })
                .filter(estado -> rojoCount.get() >= 3)
                .map(estado -> "Semáforo en Rojo detectado 3 veces seguidas")
                .subscribeOn(Schedulers.parallel());

        // Mezcla los cinco flujos
        Flux<String> eventosCriticos = Flux.merge(trafico, contaminacion, accidentes, trenes, semaforos)
                .doOnNext(System.out::println);

        // Desafío adicional: detectar eventos críticos simultáneos
        eventosCriticos.buffer(Duration.ofSeconds(1))
                .map(list -> list.size())
                .filter(size -> size >= 3)
                .map(size -> "Alerta global: Múltiples eventos críticos detectados en Meridian Prime")
                .subscribe(System.out::println);

        eventosCriticos.subscribe();

        // Mantener la app corriendo
        Thread.sleep(20000);
    }
}
