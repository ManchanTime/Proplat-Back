package architecture.lesserpanda.dto;

import architecture.lesserpanda.entity.Club;
import architecture.lesserpanda.entity.ClubStack;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public class ClubDto {
    private String name;
    private LocalDateTime dDay;
    private String title;
    private String content;
    private String url;
    private LocalDateTime nextDday;

//    public static ClubDto toClubDto(String name, LocalDateTime dDay, String title, String content, String url, LocalDateTime nextDday) {
//        return ClubDto.builder()
//                .name(name)
//                .dDay(dDay)
//                .title(title)
//                .content(content)
//                .url(url)
//                .nextDday(nextDday)
//                .build();
//    }

    public static ClubDto toClubDto(Club club) {
        return ClubDto.builder()
                .name(club.getName())
                .dDay(club.getDDay())
                .title(club.getTitle())
                .content(club.getContent())
                .url(club.getUrl())
                .nextDday(club.getNextDday())
                .build();
    }

}
