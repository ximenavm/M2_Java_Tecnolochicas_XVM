
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class MovilidadApp {

    public static CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Calculando ruta...");
            try {
                TimeUnit.SECONDS.sleep(2 + new Random().nextInt(2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Centro -> Norte";
        });
    }

    public static CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Estimando tarifa...");
            try {
                TimeUnit.SECONDS.sleep(1 + new Random().nextInt(2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 75.50;
        });
    }

    public static void main(String[] args) {
        CompletableFuture<String> rutaFuture = calcularRuta();
        CompletableFuture<Double> tarifaFuture = estimarTarifa();

        rutaFuture
                .thenCombine(tarifaFuture, (ruta, tarifa) -> 
                    "Ruta calculada: " + ruta + " | Tarifa estimada: $" + String.format("%.2f", tarifa))
                .exceptionally(e -> "Error al calcular la ruta o tarifa: " + e.getMessage())
                .thenAccept(System.out::println)
                .join();
    }
}
