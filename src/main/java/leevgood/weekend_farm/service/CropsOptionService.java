package leevgood.weekend_farm.service;

import leevgood.weekend_farm.domain.entity.product.CropsOption;
import leevgood.weekend_farm.repository.CropsOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CropsOptionService {
    private final CropsOptionRepository cropsOptionRepository;

    public List<CropsOption> getAllCropsOption(){
        return cropsOptionRepository.findAll();
    }
}
