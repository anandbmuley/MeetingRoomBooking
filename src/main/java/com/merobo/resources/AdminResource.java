package com.merobo.resources;

import com.merobo.dtos.TeamTo;
import com.merobo.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminResource {

    @Autowired
    private TeamService teamService;

    @PutMapping("/teams/{id}")
    public ResponseEntity updateTeam(@PathVariable("id") String id, TeamTo teamTo) {
        teamTo.setId(id);
        teamService.updateTeam(teamTo);
        return ResponseEntity.ok().build();
    }

}
