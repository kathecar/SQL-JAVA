package com.JPAClaseLunes.JPALunes.controller;


import com.JPAClaseLunes.JPALunes.model.Persona;
import com.JPAClaseLunes.JPALunes.service.IpersonaService;
import com.JPAClaseLunes.JPALunes.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://127.0.0.1:5500")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private IpersonaService persoServ;

    @GetMapping("/traer")
    public List<Persona> getPersonas() {
        return persoServ.getPersonas();
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearPersona(@RequestBody Persona perso) {
        persoServ.savePersona(perso);
        return ResponseEntity.ok("La persona fue creada con éxito");
    }

    @DeleteMapping("/borrar/{id_persona}")
    public ResponseEntity<String> borrarPersona(@PathVariable Long id_persona) {
        persoServ.deletePersona(id_persona);
        return ResponseEntity.ok("La persona se eliminó correctamente");
    }

    @PutMapping("/editar/{id_persona}")
    public ResponseEntity<String> editarPersona(
            @PathVariable Long id_persona,
            @RequestBody Persona personaActualizada) {
        persoServ.editarPersona(id_persona, personaActualizada);
        return ResponseEntity.ok("Se actualizó correctamente");
    }

    @GetMapping("traer/{id_persona}")
    public Persona getPersonaById(@PathVariable Long id_persona) {
        return persoServ.getPersonaById(id_persona);
    }
}
