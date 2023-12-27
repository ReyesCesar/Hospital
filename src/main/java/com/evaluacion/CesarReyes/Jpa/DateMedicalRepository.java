package com.evaluacion.CesarReyes.Jpa;

import com.evaluacion.CesarReyes.Model.DateMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DateMedicalRepository extends JpaRepository<DateMedical,Long> {

    @Query(value = "SELECT * FROM date_medical d WHERE d.consulting_room=?1 AND d.medical_consultation_schedule=?2",nativeQuery = true)
    List<DateMedical>findAllDateAndConsulting(Long idConsulting, LocalDateTime date);

    @Query(value = "SELECT * FROM date_medical dm WHERE dm.medical_consultation_schedule=?1 AND dm.id_doctor=?2",nativeQuery = true)
    List<DateMedical>findAllDateAndDoctor(LocalDateTime date, Long idDoctor);


    @Query(value = "SELECT * FROM date_medical dm WHERE  dm.id_doctor=?2",nativeQuery = true)
    List<DateMedical>findAllByIdDoctor( Long idDoctor);

    @Query(value = "SELECT * FROM date_medical dm WHERE  dm.name_of_patient=?1",nativeQuery = true)
    Optional<DateMedical> returnLastDate(String Name);

    @Query(value="SELECT * FROM date_medical dm WHERE (?1 IS NULL OR dm.id_doctor=?1) AND (?2 IS NULL  or dm.consulting_room=?2 ) OR (?3 IS NULL OR dm.medical_consultation_schedule BETWEEN ?3 AND ?4 ) ",nativeQuery = true)
    List<DateMedical>findFilterVersion(Long idDoctor,Long idRoom ,LocalDateTime from ,LocalDateTime to);

}
