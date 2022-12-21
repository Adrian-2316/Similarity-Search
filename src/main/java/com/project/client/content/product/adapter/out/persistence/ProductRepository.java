package com.project.client.content.product.adapter.out.persistence;

import com.project.client.content.product.adapter.out.persistence.entity.ProductEntity;
import com.project.client.content.product.adapter.out.persistence.entity.ProductJpaRepository;
import com.project.client.content.product.service.ports.out.ProductRepositoryPort;
import com.project.client.shared.similarity.Indexer;
import com.project.client.shared.similarity.Searcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
@Slf4j
public class ProductRepository implements ProductRepositoryPort {

  @Autowired private ProductJpaRepository productJpaRepository;

  /**
   * This method will return a list of products that match the search term.
   * @param id The id of the product.
   * @return A list of products that match the search term.
   * @throws IOException If the index is not found.
   */
  @Override
  public List<String> getBySimilarity(String id) throws IOException {
    ProductEntity productEntity = get(id);
    Indexer indexer = new Indexer();
    indexer.index("products", productJpaRepository.findAll());
    Searcher searcher = new Searcher();
    return searcher.search("products", productEntity);
  }

  /**
   * Method used to get a product by id an exception will be thrown if it does not exist.
   * @param id Product id.
   * @return ProductEntity.
   */
  public ProductEntity get(String id) {
    return productJpaRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product not found"));
  }
}
