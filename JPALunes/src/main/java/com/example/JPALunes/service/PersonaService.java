package com.example.JPALunes.service;


import com.example.JPALunes.model.Persona;
import com.example.JPALunes.repository.IpersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService implements IpersonaService{

    @Autowired
    private IpersonaRepository personaRepository;

    @Override
    public List<Persona> getPersonas(){
        List<Persona> listaPersonas = personaRepository.findAll();
        return listaPersonas;
    }

    @Override
    public void savePersona(Persona perso){
        personaRepository.save(perso);
    }

    @Override
    public void deletePersona(Long id){
        personaRepository.deleteById(id);
    }

    @Override
    public Persona findPersona (Long id){
        Persona perso = personaRepository.findById(id).orElse(null);
        return perso;
    }

    @Override
    public void editarPersona ( Long id, Persona personaActualizada){
        Persona personaExistente = personaRepository.findById(id).orElse(null);

        if(personaExistente != null){
            //Update de los datos
            //Entro a modificar con setters y get para recibirlos

            personaExistente.setNombre(personaActualizada.getNombre());
            personaExistente.setApellido(personaActualizada.getApellido());
            personaExistente.setEdad(personaActualizada.getEdad());

            //GUardar
            personaRepository.save(personaExistente);
        }
        else {
            System.out.println("La persona no se encontro, segun el id: " + id);
        }
    }

}
