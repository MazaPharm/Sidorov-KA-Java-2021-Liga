package org.example.service;

import org.example.entity.Groups;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    public Groups createGroup(){
        Groups group = new Groups();
        group.setName("Group");
        return group;
    }


    public Groups updateGroup(Groups group, String name){
        group.setName(name);
        return group;
    }


}
