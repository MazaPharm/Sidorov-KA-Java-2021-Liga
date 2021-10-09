package org.example.controller;

import org.example.repository.GroupRepository;
import org.example.entity.Groups;
import org.example.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    @GetMapping("/all")
    public String findGroups(Model model){
        List<Groups> groupsList = groupRepository.findAll();
        model.addAttribute("groups", groupsList);
        return "findGroup";
    }
    @PostMapping("/create")
    public String createGroup(){
        groupRepository.save(groupService.createGroup());
        return "redirect:/groups/all";
    }
    @GetMapping("/{id}/update")
    public String updateGroup(Model model, @PathVariable("id") Long id){
        Optional<Groups> groups = groupRepository.findById(id);
        model.addAttribute("groups", groups.get());
        return "update_delete";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @RequestParam("name") String name){
        Optional<Groups> groups = groupRepository.findById(id);
        Groups groups1 = groups.get();
        groupRepository.save(groupService.updateGroup(groups1,name));
        return "redirect:/groups/all";
    }
   // @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/delete")
    public String deleteGroup(@PathVariable("id") Long id){
        groupRepository.deleteById(id);
        return "redirect:/groups/all";
    }
}
