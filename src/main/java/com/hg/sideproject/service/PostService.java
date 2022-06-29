package com.hg.sideproject.service;

import com.hg.sideproject.domain.post.Post;
import com.hg.sideproject.domain.post.PostRepository;
import com.hg.sideproject.dto.post.PostCommand;
import com.hg.sideproject.dto.post.PostRepresentation;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Page<PostRepresentation.GetPosts.Post> pagePosts(String title, Pageable page) {
        Page<Post> posts = postRepository.findByTitleContainsAndDeletedYn(
            title, false, page);

        return posts.map(PostRepresentation.GetPosts.Post::from);
    }

    @Transactional
    public PostRepresentation.Create.Post createPost(PostCommand.Create command) {
        Post post = new Post(command.getTitle(), command.getContent());
        Post saved = postRepository.save(post);
        return PostRepresentation.Create.Post.from(saved);
    }

    @Transactional
    public PostRepresentation.Create.Post updatePost(Long postId, PostCommand.Create command)
        throws Exception {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("post not exists"));
        post.updatePost(command.getTitle(), command.getContent());
        Post saved = postRepository.save(post);
        return PostRepresentation.Create.Post.from(saved);
    }

    public PostRepresentation.GetPost getPost(Long postId) throws Exception {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("post not exists"));
        return PostRepresentation.GetPost.from(post);
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        post.deletePost();
        postRepository.save(post);
    }
}
