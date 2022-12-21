package com.project.client.shared.similarity;

import com.project.client.content.product.adapter.out.persistence.entity.ProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoublePoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Searcher {

  /**
   * Method used to build queries for the search engine.
   *
   * @param indexPath The path to the index.
   * @param product The product to search for.
   * @return A list of documents that match the query.
   * @throws IOException If the index is not found.
   */
  public List<String> search(String indexPath, ProductEntity product) throws IOException {

    double price = Math.floor(product.getPrice());
    log.info(
        "Searching for products with similar properties to: (Name) "
            + product.getName()
            + " (Price) "
            + price
            + " (Availability) "
            + product.getAvailability());

    // Open the index and create an IndexSearcher to perform the search
    IndexReader indexReader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
    IndexSearcher indexSearcher = new IndexSearcher(indexReader);

    // Create a BooleanQuery.Builder to combine multiple query clauses
    BooleanQuery.Builder builder = new BooleanQuery.Builder();

    // Add a TermQuery for matching the name of the product
    Term nameTerm = new Term("name", product.getName());
    Query nameQuery = new TermQuery(nameTerm);
    builder.add(nameQuery, BooleanClause.Occur.MUST);

    // Add a DoublePoint.newRangeQuery for matching the price of the product within a range
    Query priceRangeQuery = DoublePoint.newRangeQuery("price", price - 30, price + 30);
    builder.add(priceRangeQuery, BooleanClause.Occur.FILTER);

    // Add a TermQuery for matching the availability of the product
    Term availabilityTerm = new Term("availability", product.getAvailability().toString());
    Query availabilityQuery = new TermQuery(availabilityTerm);
    builder.add(availabilityQuery, BooleanClause.Occur.FILTER);

    // Build the BooleanQuery and perform the search
    BooleanQuery query = builder.build();
    TopDocs topDocs = indexSearcher.search(query, 10);

    // Get the total number of matching documents
    long value = topDocs.totalHits.value;
    log.info(String.format("Found %d hits ", value - 1));
    return getResults(indexSearcher, topDocs);
  }

  /**
   * Method used to get the results of the search.
   * @param indexSearcher IndexSearcher.
   * @param topDocs TopDocs.
   * @return List of results.
   * @throws IOException IOException.
   */
  private List<String> getResults(IndexSearcher indexSearcher, TopDocs topDocs) throws IOException {
    ScoreDoc[] results = topDocs.scoreDocs;
    List<String> resultsList = new ArrayList<>();
    for (ScoreDoc scoreDoc : Arrays.stream(results).limit(3).toArray(ScoreDoc[]::new)) {
      int docId = scoreDoc.doc;
      Document document = indexSearcher.doc(docId);
      resultsList.add(document.getField("id").stringValue());
    }
    return resultsList;
  }
}
