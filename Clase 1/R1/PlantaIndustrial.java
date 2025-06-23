
import java.util.ArrayList;
import java.util.List;

// Clase abstracta
abstract class OrdenProduccion {
    protected String codigo;
    protected int cantidad;

    public OrdenProduccion(String codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    public abstract void mostrarResumen();
}

// Subclase: OrdenMasa
class OrdenMasa extends OrdenProduccion {
    public OrdenMasa(String codigo, int cantidad) {
        super(codigo, cantidad);
    }

    @Override
    public void mostrarResumen() {
        System.out.println("🔧 OrdenMasa - Código: " + codigo + " - Cantidad: " + cantidad);
    }
}

// Subclase: OrdenPersonalizada
class OrdenPersonalizada extends OrdenProduccion {
    private String cliente;

    public OrdenPersonalizada(String codigo, int cantidad, String cliente) {
        super(codigo, cantidad);
        this.cliente = cliente;
    }

    public void agregarCostoAdicional(int costo) {
        System.out.println("Orden " + codigo + " ajustada con costo adicional de $" + costo);
    }

    @Override
    public void mostrarResumen() {
        System.out.println("OrdenPersonalizada - Código: " + codigo + " - Cantidad: " + cantidad + " - Cliente: " + cliente);
    }
}

// Subclase: OrdenPrototipo
class OrdenPrototipo extends OrdenProduccion {
    private String faseDesarrollo;

    public OrdenPrototipo(String codigo, int cantidad, String faseDesarrollo) {
        super(codigo, cantidad);
        this.faseDesarrollo = faseDesarrollo;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("OrdenPrototipo - Código: " + codigo + " - Cantidad: " + cantidad + " - Fase: " + faseDesarrollo);
    }
}

// Clase principal
public class PlantaIndustrial {

    // Método genérico para mostrar órdenes
    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
        }
        System.out.println();
    }

    // Método para procesar órdenes personalizadas
    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        System.out.println("Procesando órdenes personalizadas...");
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada) {
                ((OrdenPersonalizada) obj).agregarCostoAdicional(costoAdicional);
            }
        }
        System.out.println();
    }

    // Desafío adicional: contar órdenes
    public static void contarOrdenes(List<OrdenProduccion> todasLasOrdenes) {
        int masa = 0, personalizadas = 0, prototipos = 0;

        for (OrdenProduccion orden : todasLasOrdenes) {
            if (orden instanceof OrdenMasa) masa++;
            else if (orden instanceof OrdenPersonalizada) personalizadas++;
            else if (orden instanceof OrdenPrototipo) prototipos++;
        }

        System.out.println("Resumen total de órdenes:");
        System.out.println("Producción en masa: " + masa);
        System.out.println("Personalizadas: " + personalizadas);
        System.out.println("Prototipos: " + prototipos);
    }

    public static void main(String[] args) {
        // Crear órdenes
        List<OrdenMasa> ordenesMasa = new ArrayList<>();
        ordenesMasa.add(new OrdenMasa("A123", 500));
        ordenesMasa.add(new OrdenMasa("A124", 750));

        List<OrdenPersonalizada> ordenesPersonalizadas = new ArrayList<>();
        ordenesPersonalizadas.add(new OrdenPersonalizada("P456", 100, "ClienteX"));
        ordenesPersonalizadas.add(new OrdenPersonalizada("P789", 150, "ClienteY"));

        List<OrdenPrototipo> ordenesPrototipo = new ArrayList<>();
        ordenesPrototipo.add(new OrdenPrototipo("T789", 10, "Diseño"));
        ordenesPrototipo.add(new OrdenPrototipo("T790", 5, "Pruebas"));

        // Mostrar todas las órdenes
        System.out.println("Órdenes registradas:");
        mostrarOrdenes(ordenesMasa);

        System.out.println("Órdenes registradas:");
        mostrarOrdenes(ordenesPersonalizadas);

        System.out.println("Órdenes registradas:");
        mostrarOrdenes(ordenesPrototipo);

        // Procesar órdenes personalizadas
        procesarPersonalizadas(new ArrayList<OrdenProduccion>(ordenesPersonalizadas), 200);

        // Unir todas las órdenes para contar
        List<OrdenProduccion> todas = new ArrayList<>();
        todas.addAll(ordenesMasa);
        todas.addAll(ordenesPersonalizadas);
        todas.addAll(ordenesPrototipo);

        contarOrdenes(todas);
    }
}
