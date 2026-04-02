package az.edu.ada.wm2.lab6.config;

import az.edu.ada.wm2.lab6.model.Category;
import az.edu.ada.wm2.lab6.model.Product;
import az.edu.ada.wm2.lab6.repository.CategoryRepository;
import az.edu.ada.wm2.lab6.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(ProductRepository productRepository,
                               CategoryRepository categoryRepository) {
        return args -> {

            // Categories
            Category food = new Category();
            food.setName("Food");

            Category electronics = new Category();
            electronics.setName("Electronics");

            categoryRepository.saveAll(List.of(food, electronics));

            // Products
            Product milk = Product.builder()
                    .productName("Milk")
                    .price(BigDecimal.valueOf(2.5))
                    .expirationDate(LocalDate.now().plusDays(5))
                    .categories(List.of(food))
                    .build();

            Product laptop = Product.builder()
                    .productName("Laptop")
                    .price(BigDecimal.valueOf(1200))
                    .expirationDate(null)
                    .categories(List.of(electronics))
                    .build();

            productRepository.saveAll(List.of(milk, laptop));
        };
    }
}