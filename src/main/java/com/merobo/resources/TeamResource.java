package com.merobo.resources;

import com.merobo.dtos.TeamDto;
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
    public ResponseEntity addTeam(@RequestBody TeamDto teamDto) {
        teamService.addTeam(teamDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("members")
    public ResponseEntity<List<TeamDto>> getAllTeam() {
        List<TeamDto> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @GetMapping
    public ResponseEntity<List<TeamDto>> teamList() {
        try {
            List<TeamDto> teamList = teamService.getTeamList();
            return ResponseEntity.ok(teamList);
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping
    public ResponseEntity deleteTeam(TeamDto teamDto) {
        teamService.deleteTeam(teamDto);
        return ResponseEntity.ok().build();
    }

}
