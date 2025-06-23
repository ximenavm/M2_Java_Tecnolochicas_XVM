// Edita el CommandLineRunner que cree marcas y productos, y luego los agrupe por marca
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
    public CommandLineRunner demo(ProductoRepository productoRepo, CategoriaRepository categoriaRepo, MarcaRepository marcaRepo) {
        return (args) -> {
            // Crear categoría
            Categoria tecnologia = new Categoria("Tecnología");
            categoriaRepo.save(tecnologia);

            // Crear marcas
            Marca apple = new Marca("Apple");
            Marca samsung = new Marca("Samsung");
            marcaRepo.save(apple);
            marcaRepo.save(samsung);

            // Crear y guardar productos
            productoRepo.save(new Producto("iPhone 15", "Smartphone premium", 25000.00, tecnologia, apple));
            productoRepo.save(new Producto("iPad Pro", "Tablet profesional", 18000.00, tecnologia, apple));
            productoRepo.save(new Producto("Galaxy S23", "Smartphone flagship", 22000.00, tecnologia, samsung));
            productoRepo.save(new Producto("Smart TV 55\"", "Pantalla 4K UHD", 17000.00, tecnologia, samsung));

            // Mostrar los productos agrupados por marca
            System.out.println("\n Productos por marca:");
            marcaRepo.findAll().forEach(marca -> {
                System.out.println(" " + marca.getNombre() + ":");
                productoRepo.findAll().stream()
                        .filter(p -> p.getMarca().getId().equals(marca.getId()))
                        .forEach(p -> System.out.println("   - " + p.getNombre()));
            });
        };
    }
}
