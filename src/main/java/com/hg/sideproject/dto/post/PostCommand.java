package com.hg.sideproject.dto.post;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

public class PostCommand {

    @Getter
    public static class Create {

        @NotBlank
        private String title;
        @NotBlank
        private String content;
    }
}
