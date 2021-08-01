package leevgood.weekend_farm.service;

import leevgood.weekend_farm.domain.entity.Nutrients;
import leevgood.weekend_farm.repository.NutrientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NutrientsService {
    private final NutrientsRepository nutrientsRepository;

    public List<Nutrients> findAll(){
        return nutrientsRepository.findAll();
    }
}
