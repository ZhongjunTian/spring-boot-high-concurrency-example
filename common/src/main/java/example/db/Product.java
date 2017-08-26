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
  protected Long productId;

  @Column
  protected String description;

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String toString(){
    return "id: "+productId+" desc: "+description;
  }
}
