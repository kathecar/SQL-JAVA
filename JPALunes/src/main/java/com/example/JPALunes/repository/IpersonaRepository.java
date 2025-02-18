package com.example.JPALunes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.JPALunes.model.Persona;

@Repository
public interface IpersonaRepository extends JpaRepository<Persona, Long> {

}
