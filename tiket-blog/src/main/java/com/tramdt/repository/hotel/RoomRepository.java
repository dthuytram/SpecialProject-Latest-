package com.codegym.repository.hotel;

import com.codegym.model.Employee;
import com.codegym.model.hotel.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RoomRepository extends JpaRepository<Room, Long> {

//    @PostMapping("/room/add")
//    public void createRoom(@RequestBody Room room);
//
//    @Modifying
//    @Query(value = "UPDATE `room` SET del_flag_employee = 0 WHERE id = ? ", nativeQuery = true)
//    void deleteRoom(Long id);

//    @PutMapping("/room/update")
//    public void updateRoom(@RequestBody Room room;
}