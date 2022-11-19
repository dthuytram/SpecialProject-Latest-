package com.codegym.controller.hotel;

import com.codegym.model.hotel.Room;
import com.codegym.repository.hotel.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {

//    @Autowired
//    private RoomRepository roomRepository;
//
//    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);
//
//    @PostMapping("/room/add")
//    public void createRoom(@RequestBody Room room) {
//        logger.info("Inside createRoom() method of RoomController");
//        roomRepository.createRoom(room);
//    }

//    @DeleteMapping("/room/delete/{roomId}")
//    public void deleteRoom(@PathVariable Long roomId) {
//        logger.info("Inside deleteRoom() method of RoomController");
//        roomRepository.deleteRoom(roomId);
//    }

//    @PutMapping("/room/update")
//    public void updateRoom(@RequestBody Room room) {
//        logger.info("Inside updateRoom() method of RoomController");
//        roomRepository.updateRoom(room);
//    }

}