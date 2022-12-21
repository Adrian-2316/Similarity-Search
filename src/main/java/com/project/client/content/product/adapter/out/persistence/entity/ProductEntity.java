package com.project.client.content.product.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity {
  @Id private String id;
  private String name;
  private Double price;
  private Boolean availability;

  public double getSimplePrice() {
    return Math.floor(this.getPrice());
  }
}
