package com.JPAClaseLunes.JPALunes.service;

import com.JPAClaseLunes.JPALunes.model.Persona;
import com.JPAClaseLunes.JPALunes.repository.IpersonaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PersonaServiceTest {

    @Mock
    private IpersonaRepository personaRepository;
    //Simulamos el repositorio

    @InjectMocks
    private PersonaService personaService;
    //Inyectamos el servicio con el mock

    @Test
    public void testGetPersonas() {
        //Config el mock
        Persona persona1 = new Persona(1L, "Juan", "Perez", 25);
        Persona persona2 = new Persona(2L, "Ana", "Gomez", 30);
        List<Persona> listaPersonas = Arrays.asList(persona1, persona2);
        when(personaRepository.findAll()).thenReturn(listaPersonas);

        //Probar el servicio
        List<Persona> resultado = personaService.getPersonas();

        //Verificar el resultado
        assertEquals(2, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
        verify(personaRepository, times(1)).findAll();
    }

    @Test
    public void testSavePersona() {
        //Config el mock
        Persona persona = new Persona(1L, "Juan", "Perez", 25);
        when(personaRepository.save(any(Persona.class))).thenReturn(persona);

        //Probar el servicio
        personaService.savePersona(persona);

        //Verificar que el mtodo fue llamado
        verify(personaRepository, times(1)).save(any(Persona.class));
    }

    @Test
    public void testDeletePersona() {
        //Probar el servicio
        personaService.deletePersona(1L);

        //Verificar que el mtodo fue llamado
        verify(personaRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindPersona() {
        //Config el mock
        Persona persona = new Persona(1L, "Juan", "Perez", 25);
        when(personaRepository.findById(1L)).thenReturn(Optional.of(persona));

        //Probar el servicio
        Persona resultado = personaService.findPersona(1L);

        //Verificar el resultado
        assertEquals("Juan", resultado.getNombre());
        verify(personaRepository, times(1)).findById(1L);
    }

    @Test
    public void testEditarPersona() {
        //onfigr el mock
        Persona personaExistente = new Persona(1L, "Juan", "Perez", 25);
        Persona personaActualizada = new Persona(1L, "Juan Carlos", "Perez", 30);
        when(personaRepository.findById(1L)).thenReturn(Optional.of(personaExistente));
        when(personaRepository.save(any(Persona.class))).thenReturn(personaActualizada);

        //Probar el servicio
        personaService.editarPersona(1L, personaActualizada);

        //Verificar el resultado
        verify(personaRepository, times(1)).findById(1L);
        verify(personaRepository, times(1)).save(any(Persona.class));
    }
}