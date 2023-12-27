package com.evaluacion.CesarReyes.Services;

import com.evaluacion.CesarReyes.Jpa.ConsultingRoomRepository;
import com.evaluacion.CesarReyes.Model.ConsultingRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ConsultingRoomServices {

    @Autowired
    ConsultingRoomRepository consultingRoomRepository;


    public List<ConsultingRoom> getAll() {
        return consultingRoomRepository.findAll();
    }

    public ConsultingRoom findById(Long id) {
        return consultingRoomRepository.findById(id).orElseThrow();
    }

    public ConsultingRoom save(ConsultingRoom newData) {
        return consultingRoomRepository.save(newData);
    }

    public ConsultingRoom update(ConsultingRoom newData, Long id) {
        ConsultingRoom oldData = findById(id);
        oldData.setMedicalOfficeNumber(newData.getMedicalOfficeNumber());
        oldData.setFloor(newData.getFloor());
        consultingRoomRepository.save(oldData);
        return oldData;
    }

    public void delete(Long id) {
        consultingRoomRepository.deleteById(id);
    }


}
