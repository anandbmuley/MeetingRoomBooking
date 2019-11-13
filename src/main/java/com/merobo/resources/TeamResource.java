package com.merobo.resources;

import com.merobo.dtos.TeamTo;
import com.merobo.exceptions.NoDataFoundException;
import com.merobo.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teams")
public class TeamResource {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public ResponseEntity addTeam(@RequestBody TeamTo teamTo) {
        teamService.addTeam(teamTo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("members")
    public ResponseEntity<List<TeamTo>> getAllTeam() {
        List<TeamTo> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @GetMapping
    public ResponseEntity<List<TeamTo>> teamList() {
        try {
            List<TeamTo> teamList = teamService.getTeamList();
            return ResponseEntity.ok(teamList);
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping
    public ResponseEntity deleteTeam(TeamTo teamTo) {
        teamService.deleteTeam(teamTo);
        return ResponseEntity.ok().build();
    }

}
