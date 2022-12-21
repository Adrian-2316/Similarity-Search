package com.project.client.content.product.adapter.in.rest;

import com.project.client.content.product.service.ports.in.ProductPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Tag(name = "Products", description = "Products CRUD operations")
@RequestMapping("product")
public class ProductController {
  @Autowired private ProductPort productPort;

  @Operation(summary = "List of similar products to a given one ordered by similarity.")
  @GetMapping("{productId}/similar")
  public List<String> getBySimilarity(@PathVariable("productId") String id)
      throws IOException, ParseException {
    return productPort.getBySimilarity(id);
  }
}
