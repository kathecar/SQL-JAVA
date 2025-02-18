package com.example.JPALunes.service;

import com.example.JPALunes.model.Persona;

import java.util.List;

public interface IpersonaService {

    // Metodo traer perdons Read o lectura
    public List<Persona> getPersonas();

    //Metodo para guardar o dar de alta a una person
    public void savePersona(Persona perso);

    //Metodo para borrar una persona
    //Lo mejor es borrar segun el id
    public void deletePersona(Long id);

    //Metodo para buscar persona por el id
    public Persona findPersona (Long id);

    //Metodo para editar a la persona
    void editarPersona(Long id, Persona personaActualizada);
}
