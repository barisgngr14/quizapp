package com.barisgngr14.services.impl;

import com.barisgngr14.dto.DtoGroup;
import com.barisgngr14.entities.Group;
import com.barisgngr14.mappers.GroupMapper;
import com.barisgngr14.repositories.GroupRepository;
import com.barisgngr14.services.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<DtoGroup> getAllGroups() {
        List<Group> dbGroups = groupRepository.findAll();
        return dbGroups.stream()
                .map(GroupMapper::toDtoGroup)
                .collect(Collectors.toList());
    }
}
