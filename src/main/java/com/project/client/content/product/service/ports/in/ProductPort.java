package com.project.client.content.product.service.ports.in;

import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.List;

public interface ProductPort {

  List<String> getBySimilarity(String id) throws IOException, ParseException;
}
