package com.example.ChatterBox.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection="rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    private String id;

    private  String roomId;

    private List<Message> messageList = new ArrayList<>();



}
