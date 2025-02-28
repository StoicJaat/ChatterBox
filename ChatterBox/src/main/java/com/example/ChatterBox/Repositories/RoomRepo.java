package com.example.ChatterBox.Repositories;

import com.example.ChatterBox.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepo extends MongoRepository<Room,String> {

    Room findRoomByRoomId (String roomId);
}
