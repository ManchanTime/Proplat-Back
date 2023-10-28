package architecture.lesserpanda.controller;

import architecture.lesserpanda.dto.TechStackDto;
import architecture.lesserpanda.entity.TechType;
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
        RequestSave techStack1 = new RequestSave("IOS", TechType.ENVIRONMENT);
        RequestSave techStack2 = new RequestSave("java", TechType.ROLE);
        RequestSave techStack3 = new RequestSave("c", TechType.ROLE);
        RequestSave techStack4 = new RequestSave("c++", TechType.ROLE);
        RequestSave techStack5 = new RequestSave("python", TechType.ROLE);
        RequestSave techStack6 = new RequestSave("Android", TechType.ENVIRONMENT);
        techStackService.saveTech(techStack1);
        techStackService.saveTech(techStack2);
        techStackService.saveTech(techStack3);
        techStackService.saveTech(techStack4);
        techStackService.saveTech(techStack5);
        techStackService.saveTech(techStack6);
    }
}
