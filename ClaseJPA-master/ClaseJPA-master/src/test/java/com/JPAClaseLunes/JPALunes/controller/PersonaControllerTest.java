package com.JPAClaseLunes.JPALunes.controller;

import com.JPAClaseLunes.JPALunes.model.Persona;
import com.JPAClaseLunes.JPALunes.service.IpersonaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//Usamos esta anotación en vez de @SpringBootTest
public class PersonaControllerTest {

    @Mock
    private IpersonaService persoServ;
    //Simulamos el servicio

    @InjectMocks
    private PersonaController personaController;
    //Inyectamos el controlador con el mock

    @Test
    public void testGetPersonas() {
        //Config el mock
        Persona persona1 = new Persona(1L, "Juan", "Perez", 25);
        Persona persona2 = new Persona(2L, "Ana", "Gomez", 30);
        List<Persona> listaPersonas = Arrays.asList(persona1, persona2);
        when(persoServ.getPersonas()).thenReturn(listaPersonas);

        //Ejecutar el mtodo del controlador
        List<Persona> respuesta = personaController.getPersonas();

        //erificar el resultado
        assertEquals(2, respuesta.size());
        assertEquals("Juan", respuesta.get(0).getNombre());
        verify(persoServ, times(1)).getPersonas();
    }

    @Test
    public void testCrearPersona() {
        //Config el mock
        Persona persona = new Persona(1L, "Juan", "Perez", 25);
        doNothing().when(persoServ).savePersona(any(Persona.class));

        //Ejecutar el mtodo del controlador
        ResponseEntity<String> respuesta = personaController.crearPersona(persona);

        //Verificar el resultado
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("La persona fue creada con éxito", respuesta.getBody());
        verify(persoServ, times(1)).savePersona(any(Persona.class));
    }

    @Test
    public void testBorrarPersona() {
        //Config el mock
        doNothing().when(persoServ).deletePersona(1L);

        //Ejecutar el mtodo del controlador
        ResponseEntity<String> respuesta = personaController.borrarPersona(1L);

        //Verificar el resultado
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("La persona se eliminó correctamente", respuesta.getBody());
        verify(persoServ, times(1)).deletePersona(1L);
    }

    @Test
    public void testEditarPersona() {
        //Config el mock
        Persona personaActualizada = new Persona(1L, "Juan Carlos", "Perez", 30);
        doNothing().when(persoServ).editarPersona(1L, personaActualizada);

        //Ejecutar el mtodo del controlador
        ResponseEntity<String> respuesta = personaController.editarPersona(1L, personaActualizada);

        //Verificar el resultado
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("Se actualizó la perso correctamente", respuesta.getBody());
        verify(persoServ, times(1)).editarPersona(1L, personaActualizada);
    }

    @Test
    public void testGetPersonaById() {
        //Config el mock
        Persona persona = new Persona(1L, "Juan", "Perez", 25);
        when(persoServ.getPersonaById(1L)).thenReturn(persona);

        //Ejecutar el mtodo del controlador
        Persona respuesta = personaController.getPersonaById(1L);

        //Verificar el resultado
        assertNotNull(respuesta);
        assertEquals(1L, respuesta.getId_persona());
        assertEquals("Juan", respuesta.getNombre());
        verify(persoServ, times(1)).getPersonaById(1L);
    }
}
