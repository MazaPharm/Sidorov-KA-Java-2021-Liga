package org.example.controller;

import org.example.entity.Groups;
import org.example.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/all")
    public String findGroups(Model model){
        return groupService.findAll(model);
    }

    @PostMapping("/create")
    public String createGroup(@RequestParam("name") String name){
        return groupService.save(groupService.createGroup(name));
    }

    @GetMapping("/{id}/update")
    public String updateGroup(Model model, @PathVariable("id") Long id){
        model.addAttribute("groups", groupService.findById(id));
        return "update_delete";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @RequestParam("name") String name){
        Groups group = groupService.findById(id);
        return groupService.save(groupService.updateGroup(group,name));
    }

    @PostMapping("/{id}/delete")
    public String deleteGroup(@PathVariable("id") Long id){
        return groupService.deleteById(id);
    }
}
