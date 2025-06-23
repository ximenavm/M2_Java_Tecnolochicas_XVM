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
    public CommandLineRunner demo(ProductoRepository productoRepo, CategoriaRepository categoriaRepo) {
        return (args) -> {
            // Creamos una categoría
            Categoria tecnologia = new Categoria("Tecnología");
            categoriaRepo.save(tecnologia);

            // Guardamos algunos productos
            productoRepo.save(new Producto("Laptop ASUS ROG Strix SCAR 18", "Intel Core i9, RTX 5090", 90000.00, tecnologia));
            productoRepo.save(new Producto("Laptop MSI Titan 18 HX", "Intel Core i9, RTX 4090", 140000.00, tecnologia));
            productoRepo.save(new Producto("Tablet Lenovo", "Pantalla 10 pulgadas", 7800.00, tecnologia));

            // Mostrar todos los productos
            System.out.println("\n📂 Productos registrados:");
            productoRepo.findAll().forEach(p ->
                System.out.println(p.getNombre() + " - " + p.getCategoria().getNombre())
            );
        };
    }
}
