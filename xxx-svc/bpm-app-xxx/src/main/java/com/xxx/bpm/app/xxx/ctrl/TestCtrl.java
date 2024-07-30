package com.xxx.bpm.app.xxx.ctrl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/Test")
@RequiredArgsConstructor
public class TestCtrl {
    @GetMapping("")
    public String get(){
        return "Test Get";
    }
}
