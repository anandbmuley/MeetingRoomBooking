package com.merobo.resources;

import com.merobo.dtos.RoomDto;
import com.merobo.dtos.TeamTo;
import com.merobo.services.RoomService;
import com.merobo.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("admin")
public class AdminResource {

    private final TeamService teamService;
    private final RoomService roomService;

    @Autowired
    public AdminResource(TeamService teamService, RoomService roomService) {
        this.teamService = teamService;
        this.roomService = roomService;
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity updateTeam(@PathVariable("id") String id, TeamTo teamTo) {
        teamTo.setId(id);
        teamService.updateTeam(teamTo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("rooms")
    public ResponseEntity addRoom(@RequestBody RoomDto roomDto) {
        return ResponseEntity.created(
                URI.create(
                        roomService.save(roomDto)
                )
        ).build();
    }

    @GetMapping("rooms")
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        return ResponseEntity.ok(roomService.fetchAll());
    }

}
