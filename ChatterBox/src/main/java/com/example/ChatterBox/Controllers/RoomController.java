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


@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomRepo roomRepo;

    public RoomController(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId){

        Room foundRoom = roomRepo.findRoomByRoomId(roomId);
        if(foundRoom != null){
            return ResponseEntity.ok(foundRoom);
        }

        return ResponseEntity.badRequest().body("room not found");
    }

    @PostMapping("/{roomId}")
    public ResponseEntity<?> createRoom(@PathVariable String roomId){


        Room foundRoom = roomRepo.findRoomByRoomId(roomId);
        if(foundRoom == null){
            Room newRoom = new Room();
            newRoom.setRoomId(roomId);
            Room saveRoom = roomRepo.save(newRoom);

            return  ResponseEntity.status(HttpStatus.CREATED).body(newRoom +"Room created successfully." );

        }
            return ResponseEntity.badRequest().body("Room already exists");
    }

    @PutMapping("/update/{roomId}")
    public ResponseEntity<?> updateRoom(@RequestBody Room room, @PathVariable String roomId){

        Room foundRoom = roomRepo.findRoomByRoomId(roomId);

        return null;
    }
    
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String roomId , @RequestParam(value = "page", defaultValue = "0", required = false) int page, @RequestParam(value = "size", defaultValue = "20", required = false) int size){

        Room foundRoom = roomRepo.findRoomByRoomId(roomId);

        if(foundRoom == null){
            return ResponseEntity.badRequest().build();
        }
//        get messages & get pagination
        List<Message> messages = foundRoom.getMessageList();

        int start = Math.max(0,messages.size()-(page+1)*size);
        int end = Math.min(messages.size(), start + size);

        List<Message> paginatedMessages = messages.subList(start,end);


        return  ResponseEntity.ok(paginatedMessages);

    }
}
