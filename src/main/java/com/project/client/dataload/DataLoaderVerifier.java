package com.project.client.dataload;

import com.project.client.content.product.adapter.out.persistence.entity.ProductEntity;
import com.project.client.content.product.adapter.out.persistence.entity.ProductJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DataLoaderVerifier {
  @Autowired private ProductJpaRepository productJpaRepository;

  public void insertProducts(List<ProductEntity> products) {
    // Paginate the list of products to avoid memory issues
    int pageSize = 5000;
    int pages = products.size() / pageSize;
    for (int i = 0; i < pages; i++) {
      List<ProductEntity> subList = products.subList(i * pageSize, (i + 1) * pageSize);
      List<ProductEntity> productEntityList = productJpaRepository.saveAll(subList);
      log.info("A total of " + productEntityList.size() + " were created with randomized fields.");
    }
  }
}
