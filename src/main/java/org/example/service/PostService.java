package org.example.service;

import org.example.entity.Post;
import org.example.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepo postRepo;

    private final GroupService groupService;

    @Autowired
    public PostService(PostRepo postRepo, GroupService groupService) {
        this.postRepo = postRepo;
        this.groupService = groupService;
    }

    public String update(Long id,String text) {
        Post post = findById(id);
        post.setText(text);
        postRepo.save(post);
        return "Updated successfully";
    }

    @Transactional
    public Post create(Post post, Long id) {
        post.setGroup(groupService.findById(id));
        postRepo.save(post);
        return post;
    }

    @Transactional
    public String delete(Long id) {
        postRepo.deleteById(id);
        return "Post has been deleted";
    }

    public List<Post> findAll(Long id) {
        return postRepo.findByGroupsId(id);
    }

    public Post findById(Long id) {
        Optional<Post> postFound = postRepo.findById(id);
        return  postFound.get();
    }

}

