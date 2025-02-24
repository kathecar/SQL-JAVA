package com.JPAClaseLunes.JPALunes.service;

import com.JPAClaseLunes.JPALunes.model.Persona;
import com.JPAClaseLunes.JPALunes.repository.IpersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonaService implements IpersonaService{

    @Autowired
    private IpersonaRepository personaRepository;

    @Override
    public List<Persona> getPersonas() {
        List<Persona> listaPersonas = personaRepository.findAll();
        return listaPersonas;
    }

    @Override
    public void savePersona(Persona perso) {
        personaRepository.save(perso);
    }

    @Override
    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
        Persona perso = personaRepository.findById(id).orElse(null);
        return perso;
    }

    public Persona getPersonaById(Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    @Override
    public void editarPersona(Long id, Persona personaActualizada) {
        //Busque la persona por id
        Persona personaExistente = personaRepository.findById(id).orElse(null);
        if(personaExistente != null){
            //Update de los datos dw la persona
            personaExistente.setNombre(personaActualizada.getNombre());
            personaExistente.setApellido(personaActualizada.getApellido());
            personaExistente.setEdad(personaActualizada.getEdad());
            //guardar a la persona en la BD
            personaRepository.save(personaExistente);
        } else {
            System.out.println("No se encontró ninguna persona con el id: " + id);
        }
    }
}
