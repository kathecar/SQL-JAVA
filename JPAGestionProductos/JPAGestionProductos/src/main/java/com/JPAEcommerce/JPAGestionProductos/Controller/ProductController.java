package com.JPAEcommerce.JPAGestionProductos.Controller;

import com.JPAEcommerce.JPAGestionProductos.Service.IproductService;
import com.JPAEcommerce.JPAGestionProductos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IproductService productServ;

    @GetMapping("/getProduct")
    public List<Product>getProdcut(){
        return productServ.getProduct();
    }

    @GetMapping("/getProduct/{id}")
    public Product encontrarId(@PathVariable Long id){
        return productServ.getProductById(id);
    }
    @PostMapping("/crearProduct")
    public String savePersona(@RequestBody Product product){
        try {
            productServ.createProduct(product);
            return "El producto fue creado con exito";
        } catch (Exception e ){
            return e.getMessage();
            // con el try catch capturo la excepcion de la logica en productService, para mostrar
        }
    }
    @PutMapping ("/UpdateProduct/{id}")
    public ResponseEntity<String> UpdateProduct(
        @PathVariable Long id,
        @RequestBody Product productDetails){
            productServ.UpdateProduct(id, productDetails);
            return ResponseEntity.ok("Se actualizó correctamente");
    }

    @DeleteMapping ("/deleteProduct/{id}")
// coloco las llaves en algo que voy cambienado
    public String deleteProduct(@PathVariable Long id){
        productServ.deleteProduct(id);
        return "El producto se elimino con exito";
    }


}
