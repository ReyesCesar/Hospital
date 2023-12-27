package com.evaluacion.CesarReyes.Jpa;


import com.evaluacion.CesarReyes.Model.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorsRepository extends JpaRepository<Doctors,Long> {
}
