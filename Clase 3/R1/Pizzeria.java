
import java.util.*;
import java.util.stream.Collectors;

class Pedido {
    private String cliente;
    private String tipoEntrega;
    private String telefono;

    public Pedido(String cliente, String tipoEntrega, String telefono) {
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public Optional<String> getTelefono() {
        return Optional.ofNullable(telefono);
    }
}

public class Pizzeria {
    public static void main(String[] args) {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("Juan", "domicilio", "555-1234"));
        pedidos.add(new Pedido("Lucía", "local", "555-9999"));
        pedidos.add(new Pedido("Carlos", "domicilio", null));
        pedidos.add(new Pedido("Marta", "domicilio", "555-5678"));

        pedidos.stream()
                .filter(p -> "domicilio".equals(p.getTipoEntrega())) // solo entregas a domicilio
                .map(Pedido::getTelefono) // Optional<String>
                .flatMap(Optional::stream) // solo los que tienen número
                .map(num -> "Confirmación enviada al número: " + num)
                .forEach(System.out::println);
    }
}
