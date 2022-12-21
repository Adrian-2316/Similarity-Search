package com.project.client.content.product.service.ports.out;

import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.List;

public interface ProductRepositoryPort {
  List<String> getBySimilarity(String id) throws IOException, ParseException;
}
