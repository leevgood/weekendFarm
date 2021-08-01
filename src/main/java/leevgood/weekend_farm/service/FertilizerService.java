package leevgood.weekend_farm.service;

import leevgood.weekend_farm.domain.entity.Fertilizer;
import leevgood.weekend_farm.repository.FertilizerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FertilizerService {
    private final FertilizerRepository fertilizerRepository;

    public List<Fertilizer> findAll(){
        return fertilizerRepository.findAll();
    }
}
