package architecture.lesserpanda.controller;

import architecture.lesserpanda.dto.TechStackDto;
import architecture.lesserpanda.entity.Club;
import architecture.lesserpanda.entity.ClubStack;
import architecture.lesserpanda.entity.TechStack;
import architecture.lesserpanda.entity.TechType;
import architecture.lesserpanda.repository.ClubRepository;
import architecture.lesserpanda.service.ClubService;
import architecture.lesserpanda.service.TechStackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static architecture.lesserpanda.dto.TechStackDto.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TechStackService techStackService;
    private final ClubService clubService;
    private final ClubRepository clubRepository;

    @PostMapping("/tech-stack-insert")
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

    @PostMapping("/club-insert")
    public void testClub(){
        TechStack techStack1 = techStackService.findTech(1L).orElseThrow();
        ClubStack clubStack1 = ClubStack.createClubStack(techStack1);
        TechStack techStack2 = techStackService.findTech(2L).orElseThrow();
        ClubStack clubStack2 = ClubStack.createClubStack(techStack2);
        Club club = Club.builder()
                .name("test1_club_name")
                .title("test1_club_title")
                .content("test1_club_content")
                .url("test1_image_url")
                .build();
        club.setClubStack(clubStack1);
        club.setClubStack(clubStack2);
        clubRepository.save(club);
    }
}
