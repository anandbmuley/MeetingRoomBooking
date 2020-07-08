package com.merobo.resources;

import com.merobo.dtos.RoomDto;
import com.merobo.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rooms")
public class RoomResource {

    private final RoomService roomService;

    @Autowired
    public RoomResource(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("{id}")
    public ResponseEntity findOne(@PathVariable("id") String id) {
        return roomService.findOne(id).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        return ResponseEntity.ok(roomService.fetchAll());
    }

}
