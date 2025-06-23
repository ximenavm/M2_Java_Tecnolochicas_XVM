
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class AeropuertoControl {

    private static Random random = new Random();

    public static CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2 + random.nextInt(2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean disponible = random.nextDouble() < 0.8; // 80% de probabilidad
            System.out.println("Pista disponible: " + disponible);
            return disponible;
        });
    }

    public static CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2 + random.nextInt(2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean bueno = random.nextDouble() < 0.85; // 85% de probabilidad
            System.out.println("Clima favorable: " + bueno);
            return bueno;
        });
    }

    public static CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2 + random.nextInt(2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean despejado = random.nextDouble() < 0.9; // 90% de probabilidad
            System.out.println("Tráfico aéreo despejado: " + despejado);
            return despejado;
        });
    }

    public static CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2 + random.nextInt(2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean disponible = random.nextDouble() < 0.95; // 95% de probabilidad
            System.out.println("Personal disponible: " + disponible);
            return disponible;
        });
    }

    public static void main(String[] args) {
        System.out.println("Verificando condiciones para aterrizaje...\n");

        CompletableFuture<Boolean> pista = verificarPista();
        CompletableFuture<Boolean> clima = verificarClima();
        CompletableFuture<Boolean> trafico = verificarTraficoAereo();
        CompletableFuture<Boolean> personal = verificarPersonalTierra();

        CompletableFuture.allOf(pista, clima, trafico, personal)
                .thenApply(v -> pista.join() && clima.join() && trafico.join() && personal.join())
                .exceptionally(e -> {
                    System.out.println("Error durante la verificación: " + e.getMessage());
                    return false;
                })
                .thenAccept(ok -> {
                    if (ok) {
                        System.out.println("\n Aterrizaje autorizado: todas las condiciones óptimas.");
                    } else {
                        System.out.println("\n Aterrizaje denegado: condiciones no óptimas.");
                    }
                })
                .join();
    }
}
