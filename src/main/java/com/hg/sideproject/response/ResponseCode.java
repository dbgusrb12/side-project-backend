package com.hg.sideproject.response;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ResponseCode {
    OK("정상적으로 처리되었습니다.");
    private final String message;

    public String getMessage() {
        return message;
    }
}
