package architecture.lesserpanda.controller;

import architecture.lesserpanda.dto.TechStackDto;
import architecture.lesserpanda.service.TechStackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static architecture.lesserpanda.dto.TechStackDto.*;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TechStackService techStackService;

    @PostMapping("/tech-stack-api/insert")
    public void test(){
        RequestSave techStack2 = new RequestSave("java");
        RequestSave techStack3 = new RequestSave("c");
        RequestSave techStack4 = new RequestSave("c++");
        RequestSave techStack5 = new RequestSave("python");
        techStackService.saveTech(techStack2);
        techStackService.saveTech(techStack3);
        techStackService.saveTech(techStack4);
        techStackService.saveTech(techStack5);
    }
}
