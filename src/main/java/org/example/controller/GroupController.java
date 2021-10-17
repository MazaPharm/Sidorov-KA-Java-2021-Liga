package org.example.controller;

import org.example.entity.Groups;
import org.example.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     *
     * @return список всех групп
     */
    @GetMapping("/all")
    public List<Groups> findGroups(){
        return groupService.findAll();
    }

    /**
     *
     * @param groups json с названием группы
     * @return сообщение об удачном создании
     */
    @PostMapping("/create")
    public String createGroup(@RequestBody Groups groups){
        return groupService.save(groups);
    }

    /**
     *
     * @param id группы
     * @param name новоем имя группы
     * @return группа с новом именем
     */
    @PostMapping("/{id}/update")
    public Groups updateGroup( @PathVariable("id") Long id, @RequestParam("name") String name){
       return groupService.updateGroup(id, name);
    }


    /**
     *
     * @param id группы
     * @return сообщение об удачном удалении группы
     */
    @PostMapping("/{id}/delete")
    public String deleteGroup(@PathVariable("id") Long id){
        return groupService.deleteById(id);
    }
}
