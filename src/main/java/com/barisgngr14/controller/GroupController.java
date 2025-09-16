package com.barisgngr14.controller;

import com.barisgngr14.dto.DtoGroup;
import com.barisgngr14.services.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "/api/groups")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    public GroupController(IGroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(path = "/get")
    private ResponseEntity<List<DtoGroup>> getAllGroups(){
        return ResponseEntity.ok(groupService.getAllGroups());
    }
    
}
