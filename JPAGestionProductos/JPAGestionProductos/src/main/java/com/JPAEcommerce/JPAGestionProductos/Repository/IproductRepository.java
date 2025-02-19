package com.JPAEcommerce.JPAGestionProductos.Repository;

import com.JPAEcommerce.JPAGestionProductos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IproductRepository extends JpaRepository <Product, Long> {
}
