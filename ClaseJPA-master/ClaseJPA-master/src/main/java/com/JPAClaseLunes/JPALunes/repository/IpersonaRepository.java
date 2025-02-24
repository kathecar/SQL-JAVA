package com.JPAClaseLunes.JPALunes.repository;


import com.JPAClaseLunes.JPALunes.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpersonaRepository extends JpaRepository<Persona, Long> {
}
