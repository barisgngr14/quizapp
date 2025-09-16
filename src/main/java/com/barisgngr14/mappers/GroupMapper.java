package com.barisgngr14.mappers;

import com.barisgngr14.dto.DtoGroup;
import com.barisgngr14.entities.Group;

public class GroupMapper {

    public static DtoGroup toDtoGroup (Group group){
        DtoGroup dtoGroup = new DtoGroup();
        dtoGroup.setGroupId(group.getGroupId());
        dtoGroup.setGroupName(group.getGroupName());
        return dtoGroup;
    }
}
