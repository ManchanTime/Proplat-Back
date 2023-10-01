package architecture.lesserpanda.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Club {

    @Id @GeneratedValue
    @Column(name = "club_id")
    private Long id;

    private String name;
    private LocalDateTime dDay;
    private String title;
    private String content;
    private String url;
    private LocalDateTime nextDday;

    @OneToMany(mappedBy = "club")
    private List<ClubStack> clubStackList;
}
