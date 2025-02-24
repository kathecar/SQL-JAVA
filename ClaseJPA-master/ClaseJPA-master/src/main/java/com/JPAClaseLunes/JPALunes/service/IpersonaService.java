package com.JPAClaseLunes.JPALunes.service;
import com.JPAClaseLunes.JPALunes.model.Persona;
import java.util.List;

public interface IpersonaService {

    //mtodo traer personas Read o lectura
    public List<Persona> getPersonas();

    //metodo para guardar o dar de alta a una persona
    public void savePersona (Persona perso);

    //metodo paa borrar una persona
    //recibe como parametro un id
    public void deletePersona(Long id);

    //metodo para buscar personas
    public Persona findPersona(Long id);
    public Persona getPersonaById(Long id);
    //metodo para editar una persona
    void editarPersona(Long id, Persona personaActualizada);
}
