package com.evaluacion.CesarReyes.Resource;

import com.evaluacion.CesarReyes.Model.ConsultingRoom;
import com.evaluacion.CesarReyes.Services.ConsultingRoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("room")
@CrossOrigin(origins = "*")
public class ConsultingRoomResource {

    @Autowired
    ConsultingRoomServices consultingRoomServices;


    @GetMapping
    public ResponseEntity getAllOrOne(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(consultingRoomServices.findById(id));
        } else {
            return ResponseEntity.ok(consultingRoomServices.getAll());
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ConsultingRoom consultingRoom){
        return ResponseEntity.ok(consultingRoomServices.save(consultingRoom));
    }

    @PutMapping
    public ResponseEntity update(@RequestParam Long id,@RequestBody ConsultingRoom consultingRoom){
        return ResponseEntity.ok(consultingRoomServices.update(consultingRoom,id));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long id){
        consultingRoomServices.delete(id);
        return ResponseEntity.ok().build();
    }
}
