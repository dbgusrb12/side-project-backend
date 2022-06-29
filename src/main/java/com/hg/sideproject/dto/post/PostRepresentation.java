package com.hg.sideproject.dto.post;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

public class PostRepresentation {

    @Builder
    @Getter
    public static class Create {

        private Post post;

        @Builder
        @Getter
        public static class Post {

            private Long id;
            private String title;
            private String content;
            private LocalDateTime created;
            private LocalDateTime updated;

            public static Post from(com.hg.sideproject.domain.post.Post post) {
                return Post.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .created(post.getCreated())
                    .updated(post.getUpdated())
                    .build();
            }
        }
    }

    @Builder
    @Getter
    public static class GetPost {

        private Long id;
        private String title;
        private String content;
        private LocalDateTime created;
        private LocalDateTime updated;

        public static GetPost from(com.hg.sideproject.domain.post.Post post) {
            return GetPost.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .created(post.getCreated())
                .updated(post.getUpdated())
                .build();
        }
    }

    @Builder
    @Getter
    public static class GetPosts {

        private Page<Post> posts;

        @Builder
        @Getter
        public static class Post {

            private Long id;
            private String title;
            private LocalDateTime created;
            private LocalDateTime updated;

            public static Post from(com.hg.sideproject.domain.post.Post post) {
                return Post.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .created(post.getCreated())
                    .updated(post.getUpdated())
                    .build();
            }
        }
    }
}
