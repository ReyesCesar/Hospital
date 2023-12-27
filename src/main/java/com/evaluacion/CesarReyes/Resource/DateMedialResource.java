package com.evaluacion.CesarReyes.Resource;

import com.evaluacion.CesarReyes.Model.DateMedical;
import com.evaluacion.CesarReyes.Services.DateMedicalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("date")
@CrossOrigin(origins = "*")
public class DateMedialResource {

    @Autowired
    DateMedicalServices dateMedicalServices;


    @GetMapping
    public ResponseEntity getAllOrOne(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(dateMedicalServices.findByid(id));
        } else {
            return ResponseEntity.ok(dateMedicalServices.getAll());
        }
    }

    @GetMapping("/filter")
    public ResponseEntity getFilter(@RequestParam(required = false) Long idDoctor,
                                    @RequestParam(required = false) Long idRoom,
                                    @RequestParam(required = false) LocalDateTime from,
                                    @RequestParam(required = false) LocalDateTime to) {
        return ResponseEntity.ok(dateMedicalServices.findAllFilter(idDoctor, idRoom, from, to));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody DateMedical dateMedical) {
        Integer validation = dateMedicalServices.validations(dateMedical);
        switch (validation) {
            case 1:
                return ResponseEntity.status(409).body("Lo sentimos ya hay una consulta registrada a esa hora en ese consultorio");
            case 2:
                return ResponseEntity.status(409).body("Lo sentimos el doctor asignado ya tiene una consulta a esa hora");
            case 3:
                return ResponseEntity.status(409).body("Lo sentimos el paciente tiene una consulta a esas hora en proceso");
            case 4:
                return ResponseEntity.status(409).body("Lo sentimos el doctor ya llego al limite de citas");
            default:
                return ResponseEntity.ok(dateMedicalServices.save(dateMedical));
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestParam Long id, @RequestBody DateMedical dateMedical) {
        Integer validation = dateMedicalServices.validations(dateMedical);
        switch (validation) {
            case 1:
                return ResponseEntity.status(409).body("Lo sentimos ya hay una consulta registrada a esa hora en ese consultorio");
            case 2:
                return ResponseEntity.status(409).body("Lo sentimos el doctor asignado ya tiene una consulta a esa hora");
            case 3:
                return ResponseEntity.status(409).body("Lo sentimos el paciente tiene una consulta a esas hora en proceso");
            case 4:
                return ResponseEntity.status(409).body("Lo sentimos el doctor ya llego al limite de citas");
            default:
                return ResponseEntity.ok(dateMedicalServices.update(id, dateMedical));
        }
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long id) {
        Integer validate = dateMedicalServices.validateDelete(id);
        if (validate <= 1) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(409).body("Esta cita ya paso o esta en curso");
    }

}
