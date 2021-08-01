package leevgood.weekend_farm.service;

import leevgood.weekend_farm.domain.entity.Pesticide;
import leevgood.weekend_farm.repository.PesticideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PesticideService {
    private final PesticideRepository pesticideRepository;

    public List<Pesticide> findAll(){
        return pesticideRepository.findAll();
    }
}
