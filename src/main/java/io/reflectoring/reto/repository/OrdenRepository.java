package io.reflectoring.reto.repository;

import io.reflectoring.reto.entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {}