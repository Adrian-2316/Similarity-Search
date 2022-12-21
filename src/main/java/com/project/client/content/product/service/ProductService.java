package com.project.client.content.product.service;

import com.project.client.content.product.service.ports.in.ProductPort;
import com.project.client.content.product.service.ports.out.ProductRepositoryPort;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService implements ProductPort {
  @Autowired private ProductRepositoryPort productRepositoryPort;

  @Override
  public List<String> getBySimilarity(String id) throws IOException, ParseException {
    return productRepositoryPort.getBySimilarity(id);
  }
}
