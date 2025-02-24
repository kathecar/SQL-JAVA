package com.JPAEcommerce.JPAGestionProductos.service;

import com.JPAEcommerce.JPAGestionProductos.Repository.IproductRepository;
import com.JPAEcommerce.JPAGestionProductos.Service.IproductService;
import com.JPAEcommerce.JPAGestionProductos.Service.ProductService;
import com.JPAEcommerce.JPAGestionProductos.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
public class ProductServiceTest {
    @Mock
    private IproductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void tesGetProducts(){
        Product producto1 = new Product(1L, "Cafe 100% Natural", "Cafe desde la ifnca hasta el consumidor directamente", 25.000, 20);
        Product producto2 = new Product(2L, "Cacao 100% Natural", "Cacao desde la ifnca hasta el consumidor directamente", 25.000, 25);
        List<Product> listaProductos = Arrays.asList(producto1, producto2);
        when(productRepository.findAll()). thenReturn(listaProductos);

        List<Product> lista = productService.getProduct();

        //Verificamos la lista
        assertEquals(2, lista.size());
        assertEquals("Cafe 100% Natural", lista.get(0).getName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testCreateProduct() {
        //Config el mock
        Product producto = new Product(null, "Cafe 100% Natural", "Cafe desde la ifnca hasta el consumidor directamente", 25.000, 20);
        when(productRepository.save(any(Product.class))).thenReturn(producto);

        //Probar el servicio
        productService.createProduct(producto);

        //Verificar que el mtodo fue llamado
        verify(productRepository, times(1)).save(any(Product.class));
    }
    @Test
    public void testDeleteProduct() {
        //Probar el servicio
        productService.deleteProduct(1L);

        //Verificar que el mtodo fue llamado
        verify(productRepository, times(1)).deleteById(1L);
    }
    @Test
    public void testGetProductById() {
        //Config el mock
        Product persona = new Product(1L, "Cafe 100% Natural", "Cafe desde la ifnca hasta el consumidor directamente", 25.000, 20);

        when(productRepository.findById(1L)).thenReturn(Optional.of(persona));

        //Probar el servicio
        Product resultado = productService.getProductById(1L);

        //Verificar el resultado
        assertEquals("Cafe 100% Natural", resultado.getName());
        verify(productRepository, times(1)).findById(1L);
    }
    @Test
    public void testUpdateProduct() {
        //onfigr el mock
        Product producto1 = new Product(1L, "Cafe 100% Natural", "Cafe desde la ifnca hasta el consumidor directamente", 25.000, 20);
        Product productoActualizado = new Product(1L, "Cafe", "Cafe desde la finca hasta el consumidor directamente", 20.000, 20);

        // instancio dos productos primeor lo creo y en el segundo lo estoy actualizando con el segundo producto

        when(productRepository.findById(1L)).thenReturn(Optional.of(producto1));
        when(productRepository.save(any(Product.class))).thenReturn(productoActualizado);

        //Probar el servicio
        productService.UpdateProduct(1L, productoActualizado);

        //Verificar el resultado
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }


}
