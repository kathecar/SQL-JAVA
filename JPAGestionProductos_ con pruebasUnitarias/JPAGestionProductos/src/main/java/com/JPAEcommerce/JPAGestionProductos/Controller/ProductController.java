package com.JPAEcommerce.JPAGestionProductos.Controller;

import com.JPAEcommerce.JPAGestionProductos.Service.IproductService;
import com.JPAEcommerce.JPAGestionProductos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IproductService productServ;

    @GetMapping("/getProduct")
    public List<Product>getProduct(){
        return productServ.getProduct();
    }

    @GetMapping("/getProduct/{id}")
    public Product encontrarId(@PathVariable Long id){
        return productServ.getProductById(id);
    }
    @PostMapping("/crearProduct")
    public ResponseEntity<String> createProduct(@RequestBody Product product){
        try {
            productServ.createProduct(product);
            return ResponseEntity.ok("El producto fue creado con exito"
        );
        } catch (Exception e ){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el producto: " + e.getMessage());

        }
            // con el try catch capturo la excepcion de la logica en ProductService, para mostrar
    }
    @PutMapping ("/UpdateProduct/{id}")
    public ResponseEntity<String> updateProduct(
        @PathVariable Long id,
        @RequestBody Product productDetails){
            productServ.UpdateProduct(id, productDetails);
            return ResponseEntity.ok("Se actualizó correctamente");
    }

    @DeleteMapping ("/deleteProduct/{id}")
// coloco las llaves en algo que voy cambienado
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productServ.deleteProduct(id);
        return ResponseEntity.ok("El Producto se elimminó correctamente");
    }


}
