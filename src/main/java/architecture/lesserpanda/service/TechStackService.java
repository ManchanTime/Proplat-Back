package architecture.lesserpanda.service;

import architecture.lesserpanda.dto.TechStackDto;
import architecture.lesserpanda.entity.TechStack;
import architecture.lesserpanda.repository.TechStackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TechStackService {

    private final TechStackRepository techStackRepository;

    @Transactional
    public Long saveTech(TechStackDto.RequestSave requestSave){
        TechStack techStack = requestSave.toEntity();
        techStackRepository.save(techStack);
        return techStack.getId();
    }

    public Optional<TechStack> findTech(Long id){
        return techStackRepository.findById(id);
    }
}
