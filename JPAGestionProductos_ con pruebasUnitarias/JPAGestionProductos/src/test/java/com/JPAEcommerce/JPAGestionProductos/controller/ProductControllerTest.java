package com.JPAEcommerce.JPAGestionProductos.controller;

import com.JPAEcommerce.JPAGestionProductos.Controller.ProductController;
import com.JPAEcommerce.JPAGestionProductos.Repository.IproductRepository;
import com.JPAEcommerce.JPAGestionProductos.Service.IproductService;
import com.JPAEcommerce.JPAGestionProductos.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private IproductService productServ;;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testGetProduct() {
        //Config el mock
        Product producto1 = new Product(1L, "Cafe 100% Natural", "Cafe desde la ifnca hasta el consumidor directamente", 25.000, 20);
        Product producto2 = new Product(2L, "Cacao 100% Natural", "Cacao desde la ifnca hasta el consumidor directamente", 25.000, 25);
        List<Product> listaProductos = Arrays.asList(producto1, producto2);
        when(productServ.getProduct()).thenReturn(listaProductos);

        //Ejecutar el mEtodo del controlador
        List<Product> resultado = productController.getProduct();

        //verificar el resultado
        assertEquals(2, resultado.size());
        assertEquals("Cafe 100% Natural", resultado.get(0).getName());
        verify(productServ, times(1)).getProduct();
    }

    @Test
    public void testCreateProduct() {
        //Config el mock
        Product producto1 = new Product(1L, "Cafe 100% Natural", "Cafe desde la ifnca hasta el consumidor directamente", 25.000, 20);

        doNothing().when(productServ).createProduct(any(Product.class));

        //Ejecutar el mtodo del controlador
        ResponseEntity<String> resultado = productController.createProduct(producto1);

        //Verificar el resultado, 100 codigo
        assertEquals(200, resultado.getStatusCodeValue());
        assertEquals("El producto fue creado con exito", resultado.getBody());
        verify(productServ, times(1)).createProduct(any(Product.class));
    }

    @Test
    public void testDeleteProduct() {
        //Config el mock
        doNothing().when(productServ).deleteProduct(1L);

        //Ejecutar el mtodo del controlador
        ResponseEntity<String> resultado = productController.deleteProduct(1L);

        //Verificar el resultado
        assertEquals(200, resultado.getStatusCodeValue());
        assertEquals("El Producto se elimminó correctamente", resultado.getBody());
        verify(productServ, times(1)).deleteProduct(1L);
    }

    @Test
    public void testUpdateProduct() {
        //Config el mock
        Product productDetails = new Product(1L, "Cafe", "Cafe desde la finca hasta el consumidor directamente", 25.000, 20);
        doNothing().when(productServ).UpdateProduct(1L, productDetails);

        //Ejecutar el mtodo del controlador
        ResponseEntity<String> resultado = productController.updateProduct(1L, productDetails);

        //Verificar el resultado
        assertEquals(200, resultado.getStatusCodeValue());
        assertEquals("Se actualizó correctamente", resultado.getBody());
        verify(productServ, times(1)).UpdateProduct(1L, productDetails);
    }

    @Test
    public void testGetPersonaById() {
        //Config el mock
        Product producto1 = new Product(1L, "Cafe 100% Natural", "Cafe desde la finca hasta el consumidor directamente", 25.000, 20);

        when(productServ.getProductById(1L)).thenReturn(producto1);

        //Ejecutar el mtodo del controlador
        Product resultado = productController.encontrarId(1L);

        //Verificar el resultado
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Cafe 100% Natural", resultado.getName());
        verify(productServ, times(1)).getProductById(1L);
    }
}
