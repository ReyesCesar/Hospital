package com.evaluacion.CesarReyes.Resource;

import com.evaluacion.CesarReyes.Model.Doctors;
import com.evaluacion.CesarReyes.Services.DoctorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Doctors")
@CrossOrigin(origins = "*")
public class DoctorsResource {

    @Autowired
    DoctorServices doctorServices;

    @GetMapping
    public ResponseEntity getAllOrOne(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(doctorServices.findById(id));
        } else {
            return ResponseEntity.ok(doctorServices.findALl());
        }
    }


    @PostMapping
    public ResponseEntity saveNewDoctor(@RequestBody Doctors doctors){
        return ResponseEntity.ok(doctorServices.save(doctors));
    }

    @PutMapping()
    public  ResponseEntity updateDoctor(@RequestParam Long id,@RequestBody Doctors doctors){
        return ResponseEntity.ok(doctorServices.update(id,doctors));
    }

    @DeleteMapping
    public ResponseEntity deleteDoctor(@RequestParam Long id){
        doctorServices.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
