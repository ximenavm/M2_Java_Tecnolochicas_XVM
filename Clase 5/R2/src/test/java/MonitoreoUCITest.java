
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

class MonitoreoUCITest {

    @Test
    void testFiltroSignosVitalesCriticos() {
        // Creamos un Flux simulado con signos vitales que disparan eventos críticos
        Flux<SignosVitales> testFlux = Flux.just(
            new SignosVitales(45, 100, 70, 95), // FC crítica
            new SignosVitales(80, 150, 95, 98), // PA crítica
            new SignosVitales(80, 120, 80, 85)  // SpO2 crítica
        );

        Flux<String> eventos = testFlux.flatMap(sv -> {
            Flux<String> resultado = Flux.empty();
            if (sv.fc < 50 || sv.fc > 120) {
                resultado = resultado.concatWith(Flux.just("FC crítica"));
            } else if (sv.paSist > 140 || sv.paDiast > 90 || sv.paSist < 90 || sv.paDiast < 60) {
                resultado = resultado.concatWith(Flux.just("PA crítica"));
            } else if (sv.spo2 < 90) {
                resultado = resultado.concatWith(Flux.just("SpO2 baja"));
            }
            return resultado;
        });

        StepVerifier.create(eventos)
                .expectNext("FC crítica")
                .expectNext("PA crítica")
                .expectNext("SpO2 baja")
                .verifyComplete();
    }
}
