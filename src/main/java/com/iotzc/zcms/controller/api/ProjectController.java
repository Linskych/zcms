package com.iotzc.zcms.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/project")
@Slf4j(topic="api")
public class ProjectController {

    @RequestMapping("/list_project")
    public String listProject() {
        log.info("list_project");
        return "";
    }
}
