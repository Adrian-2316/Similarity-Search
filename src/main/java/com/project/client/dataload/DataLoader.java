package com.project.client.dataload;

import com.project.client.content.product.adapter.out.persistence.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
@Slf4j
public class DataLoader implements ApplicationRunner {
  private static final List<String> colors =
      List.of(
          "Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Pink", "Brown", "Gray", "Black",
          "White");
  private static final List<String> clothes =
      List.of(
          "T-shirt",
          "Shirt",
          "Jeans",
          "Pants",
          "Dress",
          "Skirt",
          "Jacket",
          "Coat",
          "Sweater",
          "Blouse",
          "Suit",
          "Dress pants",
          "Leggings",
          "Shorts",
          "Underwear",
          "Socks",
          "Shoes",
          "Boots",
          "Hat",
          "Gloves",
          "Scarf",
          "Belt",
          "Tie",
          "Sunglasses");
  private static final Random random = new Random();
  private DataLoaderVerifier dataLoaderVerifier;

  /**
   * Method used to save static values when the application starts.
   *
   * @param args ApplicationArguments.
   */
  @Override
  public void run(ApplicationArguments args) {
    List<ProductEntity> productEntities = buildProductEntities(10000);
    dataLoaderVerifier.insertProducts(productEntities);
  }

  /**
   * Method used to build a list of products.
   *
   * @param entityCount Amount of entities to create.
   * @return List of ProductEntities to create.
   */
  private List<ProductEntity> buildProductEntities(int entityCount) {
    List<ProductEntity> productEntities = new ArrayList<>();
    for (int i = 1; i <= entityCount; i++) {
      String id = String.valueOf(i);
      String name = generateRandomProduct();
      double price = generateRandomPrice();
      boolean availability = random.nextBoolean();
      productEntities.add(new ProductEntity(id, name, price, availability));
    }
    return productEntities;
  }

  /**
   * Method used to generate a random product price.
   *
   * @return Product price.
   */
  private double generateRandomPrice() {
    return Math.random() * (1000.00 - 1.00) + 1.00;
  }

  /**
   * Method used to generate a random product name.
   *
   * @return Product name.
   */
  private String generateRandomProduct() {
    String color = colors.get(random.nextInt(colors.size()));
    String item = clothes.get(random.nextInt(clothes.size()));
    return "%s %s".formatted(color, item);
  }
}
