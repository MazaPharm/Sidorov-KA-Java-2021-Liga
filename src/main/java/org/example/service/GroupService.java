package org.example.service;

import org.example.entity.Groups;
import org.example.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Groups updateGroup(Long id, String name){
        Groups group = getGroup(id);
        group.setName(name);
        return group;
    }

    public List<Groups> findAll(){
        List<Groups> groups = groupRepository.findAll();
        return groups;
    }
    @Transactional
    public String save(Groups group){
        groupRepository.save(group);
        return "Group created";
    }

    public Groups findById(Long id){
        Optional<Groups> groupFound = groupRepository.findById(id);
        Groups group = groupFound.get();
        return group;
    }
    @Transactional
    public String deleteById(Long id){
        groupRepository.deleteById(id);
        return "Group deleted";
    }

    private Groups getGroup(Long id){
        Optional<Groups> groupFound = groupRepository.findById(id);
        return groupFound.get();
    }

}
