package org.example.service;

import org.example.entity.Groups;
import org.example.entity.Post;
import org.example.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Transactional
    public Groups createGroup(){
        Groups group = new Groups();
        group.setName("Group");
        return group;
    }


    @Transactional
    public Groups updateGroup(Groups group, String name){
        group.setName(name);
        return group;
    }


    public String findAll(Model model){
        model.addAttribute("groups", groupRepository.findAll());
        return "findGroup";
    }
    @Transactional
    public String save(Groups group){
        groupRepository.save(group);
        return "redirect:/groups/all";
    }

    public Groups findById(Long id){
        Optional<Groups> groupFound = groupRepository.findById(id);
        Groups group = groupFound.get();
        return group;
    }
    @Transactional
    public String deleteById(Long id){
        groupRepository.deleteById(id);
        return "redirect:/groups/all";
    }


}
