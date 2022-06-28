package com.hg.sideproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleService {

    public String sample() {
        return "sample";
    }
}
