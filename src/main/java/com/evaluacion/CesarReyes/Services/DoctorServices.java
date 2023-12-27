package com.evaluacion.CesarReyes.Services;

import com.evaluacion.CesarReyes.Jpa.DoctorsRepository;
import com.evaluacion.CesarReyes.Model.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DoctorServices {

    @Autowired
    DoctorsRepository doctorsRepository;

    public Doctors findById(Long id) {
        return doctorsRepository.findById(id).orElseThrow();
    }

    public List<Doctors> findALl(){
        return doctorsRepository.findAll();
    }

    public Doctors update(Long id,Doctors newData){
        Doctors oldData=findById(id);
        oldData.setName(newData.getName());
        oldData.setLastName(newData.getLastName());
        oldData.setSecondLastName(newData.getSecondLastName());
        oldData.setSpecialite(newData.getSpecialite());
        doctorsRepository.save(oldData);
        return oldData;
    }

    public Doctors save(Doctors newDoctor){
        return doctorsRepository.save(newDoctor);
    }

    public void deleteById(Long id){
        doctorsRepository.deleteById(id);
    }
}
