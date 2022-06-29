package com.hg.sideproject.domain.post;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    private Post saveMock() {
        Post post = new Post("제목 테스트", "내용 테스트");
        return postRepository.save(post);
    }

    @Test
    public void save() {
        Post save = this.saveMock();
        assertThat(save.getTitle()).isEqualTo("제목 테스트");
        assertThat(save.getContent()).isEqualTo("내용 테스트");
        assertThat(save.getCreated()).isNotNull();
        assertThat(save.getUpdated()).isNotNull();
    }

    @Test
    public void update() {
        Post save = this.saveMock();
        save.updatePost("제목 수정 테스트", "내용 수정 테스트");
        postRepository.save(save);
        assertThat(save.getTitle()).isEqualTo("제목 수정 테스트");
        assertThat(save.getContent()).isEqualTo("내용 수정 테스트");
        assertThat(save.getCreated()).isNotNull();
        assertThat(save.getUpdated()).isNotNull();
    }

    @Test
    public void getPost() {
        Post save = this.saveMock();
        Post post = postRepository.findById(save.getId()).get();

        assertThat(post.getId()).isEqualTo(save.getId());
        assertThat(post).isEqualTo(save);
    }

    @Test
    public void pagePost() {
        Post save = this.saveMock();
        PageRequest page = PageRequest.of(0, 10);
        Page<Post> posts = postRepository.findByTitleContainsAndDeletedYn("",
            false, page);

        assertThat(posts.getTotalElements()).isEqualTo(1);
        assertThat(posts.getContent().get(0)).isEqualTo(save);

    }

}