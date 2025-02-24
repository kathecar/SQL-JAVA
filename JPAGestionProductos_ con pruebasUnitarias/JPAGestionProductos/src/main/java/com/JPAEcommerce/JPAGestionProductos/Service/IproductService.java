package com.JPAEcommerce.JPAGestionProductos.Service;

import com.JPAEcommerce.JPAGestionProductos.model.Product;

import java.util.List;

public interface IproductService {
    //Metodos
    // Mostrar todos los productos
    public List <Product> getProduct();

    //Mostrar de acuerdo al id
    public Product getProductById(Long id);

    //Crear Productos
    public void createProduct(Product product);

    //Actualizar producto
    public void UpdateProduct(Long id, Product productDetails);

    //Eliminar prodcuto
    public void deleteProduct(Long id);
}
