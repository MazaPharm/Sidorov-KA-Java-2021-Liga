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

    @Autowired
    private PostRepo postRepo;

    public String update(Post post) {
        post.setText("Updated post's text");
        postRepo.save(post);
        return "Updated successfully";
    }

    @Transactional
    public Post create(Post post) {
        postRepo.save(post);
        return post;
    }

    @Transactional
    public String delete(Long id) {
        postRepo.deleteById(id);
        return "Post has been deleted";
    }

    public List<Post> findAll() {
        return postRepo.findAll();
    }

    public Post findById(Long id) {
        Optional<Post> postFound = postRepo.findById(id);
        Post post = postFound.get();
        return post;
    }

}

