package com.bedu.inventario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventarioApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProductoRepository repository) {
        return (args) -> {
            // Guardar algunos productos
            repository.save(new Producto("Laptop Lenovo", "Portátil de 16 pulgadas", 12500.00));
            repository.save(new Producto("Mouse Logitech", "Alta precisión", 350.00));
            repository.save(new Producto("Teclado Mecánico", "Switch azul", 950.00));
            repository.save(new Producto("Monitor", "24 pulgadas Full HD", 3200.00));

            // Productos con precio mayor a 500
            System.out.println("\n Productos con precio mayor a 500:");
            repository.findByPrecioGreaterThan(500.00).forEach(System.out::println);

            //  Productos que contienen 'lap'
            System.out.println("\n Productos que contienen 'lap':");
            repository.findByNombreContainingIgnoreCase("lap").forEach(System.out::println);

            //  Productos con precio entre 400 y 1000
            System.out.println("\n Productos con precio entre 400 y 1000:");
            repository.findByPrecioBetween(400.00, 1000.00).forEach(System.out::println);

            // Productos cuyo nombre empieza con 'm'
            System.out.println("\n Productos cuyo nombre empieza con 'm':");
            repository.findByNombreStartingWithIgnoreCase("m").forEach(System.out::println);
        };
    }
}
