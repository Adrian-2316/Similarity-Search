package com.project.client.content.product.adapter.out.persistence.entity;

import com.project.client.content.product.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductEntityMapper {
  ProductEntityMapper INSTANCE = Mappers.getMapper(ProductEntityMapper.class);

  ProductEntity toEntity(Product product);

  Product toDomainModel(ProductEntity productEntity);
}
