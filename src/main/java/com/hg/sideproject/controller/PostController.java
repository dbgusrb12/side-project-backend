package com.hg.sideproject.controller;

import static com.hg.sideproject.constants.Constants.API_PREFIX;
import static com.hg.sideproject.constants.Constants.POST_API;

import com.hg.sideproject.dto.post.PostCommand;
import com.hg.sideproject.dto.post.PostRepresentation;
import com.hg.sideproject.response.Response;
import com.hg.sideproject.service.PostService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_PREFIX + POST_API)
public class PostController {

    private final PostService postService;

    /**
     * 게시판 조회 (페이징 처리)
     */
    @GetMapping("")
    public Response<PostRepresentation.GetPosts> pagePosts(
        @RequestParam(required = false, defaultValue = "") String title,
        @PageableDefault(sort = "created", direction = Direction.DESC) Pageable page
    ) {
        Page<PostRepresentation.GetPosts.Post> posts = postService.pagePosts(title, page);

        return new Response<>(
            PostRepresentation.GetPosts.builder()
                .posts(posts)
                .build()
        );
    }

    /**
     * 게시글 생성
     */
    @PostMapping("")
    public Response<PostRepresentation.Create> createPost(
        @RequestBody @Valid PostCommand.Create command) {
        PostRepresentation.Create.Post post = postService.createPost(command);

        return new Response<>(
            PostRepresentation.Create.builder()
                .post(post)
                .build()
        );
    }

    /**
     * 게시글 수정
     */
    @PutMapping("/{postId}")
    public Response<PostRepresentation.Create> updatePost(@PathVariable Long postId,
        @RequestBody @Valid PostCommand.Create command) throws Exception {
        PostRepresentation.Create.Post post = postService.updatePost(postId, command);
        return new Response<>(
            PostRepresentation.Create.builder()
                .post(post)
                .build()
        );
    }

    /**
     * 게시글 상세 조회
     */
    @GetMapping("/{postId}")
    public Response<PostRepresentation.GetPost> getPost(@PathVariable Long postId)
        throws Exception {
        PostRepresentation.GetPost post = postService.getPost(postId);
        return new Response<>(post);
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/{postId}")
    public Response deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return Response.ok();
    }
}
