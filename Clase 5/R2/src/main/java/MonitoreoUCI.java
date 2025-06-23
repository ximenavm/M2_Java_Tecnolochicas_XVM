
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;

class SignosVitales {
    int fc;
    int paSist;
    int paDiast;
    int spo2;

    public SignosVitales(int fc, int paSist, int paDiast, int spo2) {
        this.fc = fc;
        this.paSist = paSist;
        this.paDiast = paDiast;
        this.spo2 = spo2;
    }
}

public class MonitoreoUCI {
    private static Random random = new Random();

    public static Flux<SignosVitales> generarSignosVitales() {
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> new SignosVitales(
                        random.nextInt(60) + 40,
                        random.nextInt(60) + 80,
                        random.nextInt(40) + 50,
                        random.nextInt(10) + 85
                ))
                .subscribeOn(Schedulers.parallel());
    }

    public static Flux<String> monitorearPaciente(int idPaciente) {
        return generarSignosVitales()
                .flatMap(sv -> {
                    // Evaluar eventos
                    Flux<String> eventos = Flux.empty();

                    if (sv.fc < 50 || sv.fc > 120) {
                        eventos = eventos.concatWith(Flux.just("⚠️ Paciente " + idPaciente + " - FC crítica: " + sv.fc + " bpm"));
                    } else if (sv.paSist > 140 || sv.paDiast > 90 || sv.paSist < 90 || sv.paDiast < 60) {
                        eventos = eventos.concatWith(Flux.just("⚠️ Paciente " + idPaciente + " - PA crítica: " + sv.paSist + "/" + sv.paDiast + " mmHg"));
                    } else if (sv.spo2 < 90) {
                        eventos = eventos.concatWith(Flux.just("⚠️ Paciente " + idPaciente + " - SpO2 baja: " + sv.spo2 + "%"));
                    }

                    return eventos;
                });
    }

    public static void main(String[] args) throws InterruptedException {
        Flux<String> paciente1 = monitorearPaciente(1);
        Flux<String> paciente2 = monitorearPaciente(2);
        Flux<String> paciente3 = monitorearPaciente(3);

        Flux.merge(paciente1, paciente2, paciente3)
                .delayElements(Duration.ofSeconds(1)) // backpressure
                .subscribe(System.out::println);

        Thread.sleep(20000); // Para que la app se mantenga viva unos segundos
    }
}
