package example.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Trivial JPA db for vertx-vertx demo
 */
@Entity
@Table(name="PRODUCT")
public class Product {

  @Id
  @Column(name="ID")
  private Integer productId;

  @Column
  private String description;

  public Integer getProductId() {
    return productId;
  }

  public String getDescription() {
    return description;
  }
}
