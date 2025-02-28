package com.example.ChatterBox.Controllers;

import com.example.ChatterBox.Repositories.RoomRepo;
import com.example.ChatterBox.entities.Message;
import com.example.ChatterBox.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController("/room")
public class RoomController {

    @Autowired
    private RoomRepo roomRepo;

    @GetMapping("roombyid")
    public ResponseEntity<?> findRoom(@PathVariable String roomId){

        Room foundRoom = roomRepo.findRoomByRoomId(roomId);
        if(foundRoom != null){
            return ResponseEntity.ok(foundRoom);
        }

        return ResponseEntity.badRequest().body("room not found");
    }

    @PostMapping("/createroom")
    public ResponseEntity<?> createRoom(@PathVariable String roomID){


        Room foundRoom = roomRepo.findRoomByRoomId(roomID);
        if(foundRoom == null){
            Room newRoom = new Room();
            newRoom.setRoomId(roomID);
            Room saveRoom = roomRepo.save(newRoom);

            return  ResponseEntity.status(HttpStatus.CREATED).body(newRoom +"Room created successfully." );

        }
            return ResponseEntity.badRequest().body("Room already exists");
    }

    @PutMapping("/updateroom")
    public ResponseEntity<?> updateRoom(@RequestBody Room room, @PathVariable String roomId){

        Room foundRoom = roomRepo.findRoomByRoomId(roomId);



        return null;
    }
    
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String roomId , @RequestParam(value = "page", defaultValue = "0", required = false) int page, @RequestParam(value = "size", defaultValue = "20", required = false) int size){

//        Room foundRoom = roomRepo.findById(roomId);

//        if(foundRoom == null){
//
//        }
        return null;
    }
}
