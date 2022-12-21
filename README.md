# Similarity search engine

![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![ElasticSearch](https://img.shields.io/badge/-ElasticSearch-005571?style=for-the-badge&logo=elasticsearch)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Apache Lucene](https://img.shields.io/badge/-Apache%20Lucene-A70B0B?style=for-the-badge&logo=Apache%20Lucene&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Hexagonal Architecture](https://img.shields.io/badge/-Hexagonal%20Architecture-4C51A5?style=for-the-badge&logo=hexagonal-architecture&logoColor=white)
# Similarity Product Search using Apache Lucene

This application is a product search tool that allows users to find similar products based on their name, price, and
availability. It is implemented using Apache Lucene and built with Java and Spring Boot.
This project is a Java implementation of the Lucene library that allows users to search for products in a Lucene index
with similar properties to a given product. The search is performed using a Boolean query that combines multiple clauses
using logical operators, including a mandatory clause for matching the name of the given product, and filter clauses for
matching the price and availability within a certain range. The search is limited to the top 10 matching documents, and
the method returns a list of the IDs of the top 3 matching documents. This tool is designed to be scalable and
performant, with robust exception handling and comprehensive testing to ensure reliability and correctness.

To ensure scalability and performance, we have implemented a number of measures, including:

- Treatment of the name, price, and availability parameters of the Product class to provide more accurate search
  results.
- Use of the Hexagonal Architecture design pattern to modularize and isolate the various components of the application,
  making it easier to maintain and scale.
- Comprehensive testing to ensure the reliability and correctness of the application.
- A data loader utility to pre-populate the search index with initial data.
- Robust exception handling to gracefully handle errors and maintain the stability of the application.

## Getting Started

To get started with the application, follow these steps:

1. Install the necessary dependencies, including Java and Apache Maven.
2. Clone the repository to your local machine.
3. Run the `mvn clean install` command to build the project.
4. Run the `java -jar target/similarity.jar` command to start the application.

## Usage

To use the product search tool, send a GET request to the `product/{productId}/similarity` endpoint.

### Parameters
- `productId`: The ID of the product for which you want to find similar products.

### Returns
- A list of product IDs for similar products, ordered by similarity.

### Exceptions
- `IOException`: If there is an error reading from the Lucene index.
- `ParseException`: If there is an error parsing the query string.