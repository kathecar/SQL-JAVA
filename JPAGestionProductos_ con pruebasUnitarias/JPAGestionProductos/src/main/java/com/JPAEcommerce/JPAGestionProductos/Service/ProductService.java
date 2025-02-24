package com.JPAEcommerce.JPAGestionProductos.Service;

import com.JPAEcommerce.JPAGestionProductos.Repository.IproductRepository;
import com.JPAEcommerce.JPAGestionProductos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IproductService{

    @Autowired
    private IproductRepository productRepository;


    @Override
    public List<Product> getProduct() {
        List<Product> listaProductos = productRepository.findAll();
        return listaProductos;
    }

    @Override
    public Product getProductById(Long id) {
        Product productId = productRepository.findById(id).orElse(null);
        return productId;
    }
    @Override
    public void createProduct(Product product) {
        if (product.getId() != null) { // Si el ID ya viene en el request, es incorrecto para una creación
            throw new IllegalArgumentException("No se debe enviar un ID al crear un producto. Se generará automáticamente.");
        }
        productRepository.save(product);
    }

        //trow en java es una excepcion y el illegal  indica que el argumento es valido

    @Override
    public void UpdateProduct(Long id, Product productDetails) {
        Product productoExistente = productRepository.findById(id).orElse(null);
        if (productoExistente != null) {
            productoExistente.setName(productDetails.getName());
            productoExistente.setDescription(productDetails.getDescription());
            productoExistente.setPrice(productDetails.getPrice());
            productoExistente.setStock(productDetails.getStock());
            // Do not set the ID since it's already set and should not change
            productRepository.save(productoExistente);
        } else {

        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }
}
