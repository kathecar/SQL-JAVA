package com.JPAEcommerce.JPAGestionProductos.Service;

import com.JPAEcommerce.JPAGestionProductos.Repository.IproductRepository;
import com.JPAEcommerce.JPAGestionProductos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService implements IproductService{

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
    public Product createProduct(Product product) {

        Product productoExistente = productRepository.findById(product.getId()).orElse(null);

        if (productoExistente == null) {
            return productRepository.save(product);
        } else {
            throw new IllegalArgumentException("El producto con el Id " + product.getId() + " ya existe");
        }
        //trow en java es una excepcion y el illegal  indica que el argumento es valido
    }

    @Override
    public Product UpdateProduct(Long id, Product productDetails) {
        Product productoExistente = productRepository.findById(id).orElse(null);
        if (productoExistente != null) {
            productoExistente.setName(productDetails.getName());
            productoExistente.setDescription(productDetails.getDescription());
            productoExistente.setPrice(productDetails.getPrice());
            productoExistente.setStock(productDetails.getStock());
            // Do not set the ID since it's already set and should not change
            productRepository.save(productoExistente);
            return productoExistente; // Return the updated product
        } else {
            throw new IllegalArgumentException("El producto con el Id: " + id + " no se encuentra");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }
}
