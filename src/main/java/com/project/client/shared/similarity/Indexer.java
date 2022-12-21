package com.project.client.shared.similarity;

import com.project.client.content.product.adapter.out.persistence.entity.ProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoublePoint;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class Indexer {

  /**
   * Method used to index the products using Apache Lucene.
   *
   * @param indexPath The path to the index.
   * @param productEntityList The list of products to index.
   * @throws IOException If the index is not found.
   */
  public void index(String indexPath, List<ProductEntity> productEntityList) throws IOException {
    IndexWriter indexWriter = initializeWriter(indexPath);
    // Delete old entries (Normally would be controlled by update and creation requests, but for
    // simplicity it is handled on the index method).
    indexWriter.deleteAll();

    // Fill document entries.
    for (ProductEntity productEntity : productEntityList) {
      Document document = new Document();
      document.add(new StringField("id", productEntity.getId(), Field.Store.YES));
      document.add(new DoublePoint("price", productEntity.getSimplePrice()));
      document.add(new StringField("name", productEntity.getName(), Field.Store.YES));
      document.add(
          new StringField(
              "availability", productEntity.getAvailability().toString(), Field.Store.YES));
      indexWriter.addDocument(document);
    }
    log.info("Indexed {} documents", productEntityList.size());
    log.info("Indexed Successfully!");
    // Close the index writer.
    indexWriter.close();
  }

  /**
   * Method used to initialize the index writer.
   * @param indexPath The path to the index.
   * @return The index writer.
   * @throws IOException  If the index is not found.
   */
  private IndexWriter initializeWriter(String indexPath) throws IOException {
    Directory directory = FSDirectory.open(Paths.get(indexPath));
    Analyzer analyzer = new StandardAnalyzer();
    IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
    return new IndexWriter(directory, indexWriterConfig);
  }
}
