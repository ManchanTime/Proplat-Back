package architecture.lesserpanda.controller;

import architecture.lesserpanda.dto.ClubDto;
import architecture.lesserpanda.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clubs")
public class ClubController {
    public final ClubService clubService;

    @GetMapping
    public Page<ClubDto> getClubList(@PageableDefault Pageable pageable){

        return clubService.getClubList(pageable);

    }

}
