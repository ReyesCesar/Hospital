package com.evaluacion.CesarReyes.Services;

import com.evaluacion.CesarReyes.Jpa.DateMedicalRepository;
import com.evaluacion.CesarReyes.Model.DateMedical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DateMedicalServices {

    @Autowired
    DateMedicalRepository dateMedicalRepository;

    public List<DateMedical> getAll() {
        return dateMedicalRepository.findAll();
    }

    public DateMedical findByid(Long id) {
        return dateMedicalRepository.findById(id).orElseThrow();
    }

    public DateMedical save(DateMedical newData) {
        return dateMedicalRepository.save(newData);
    }

    public DateMedical update(Long id, DateMedical newData) {
        DateMedical oldData = findByid(id);
        oldData.setConsultingRoom(newData.getConsultingRoom());
        oldData.setIdDoctor(newData.getIdDoctor());
        oldData.setMedicalConsultationSchedule(newData.getMedicalConsultationSchedule());
        oldData.setNameOfPatient(newData.getNameOfPatient());
        dateMedicalRepository.save(oldData);
        return oldData;
    }

    public void delete(Long id) {
        dateMedicalRepository.deleteById(id);
    }


    public Integer validations(DateMedical dateMedical){
        if (dateMedicalRepository.findAllDateAndConsulting(dateMedical.getConsultingRoom(),dateMedical.getMedicalConsultationSchedule()).size()>=1){
            return 1;
        }
        if (dateMedicalRepository.findAllDateAndDoctor(dateMedical.getMedicalConsultationSchedule(), dateMedical.getIdDoctor()).size()>=1){
            return 2;
        }
        if (ValidationUser(dateMedical)){
            return 3;
        }
        if (dateMedicalRepository.findAllByIdDoctor(dateMedical.getIdDoctor()).size()>=8){
            return 4;
        }
        return 0;
    }

    public Boolean ValidationUser(DateMedical dateMedical){
        Optional<DateMedical> find=dateMedicalRepository.returnLastDate(dateMedical.getNameOfPatient());
        if(find.isEmpty()){
            return false;
        }
        LocalDateTime validation=find.get().medicalConsultationSchedule.plusHours(2);
        if(dateMedical.getMedicalConsultationSchedule().isAfter(validation)){
            return false;
        }
        return true;
    }


    public Integer  validateDelete (Long id){
        LocalDateTime now=LocalDateTime.now();
        DateMedical dateMedical=findByid(id);
        if(dateMedical.getMedicalConsultationSchedule().isAfter(now)){
            delete(id);
            return 1;
        }
        else{
            return 2;
        }
    }


    public List<DateMedical> findAllFilter(Long idDoctor,Long idRoom,LocalDateTime from,LocalDateTime to){
        return dateMedicalRepository.findFilterVersion(idDoctor,idRoom,from,to);
    }
}
