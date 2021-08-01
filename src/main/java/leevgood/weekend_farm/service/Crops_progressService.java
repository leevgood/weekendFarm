package leevgood.weekend_farm.service;

import leevgood.weekend_farm.domain.entity.Crops_progress;
import leevgood.weekend_farm.repository.Crops_progressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Crops_progressService {
    private final Crops_progressRepository crops_progressRepository;

    public List<Crops_progress> findAll(){
        return crops_progressRepository.findAll();
    }
}
