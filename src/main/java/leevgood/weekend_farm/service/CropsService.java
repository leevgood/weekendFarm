package leevgood.weekend_farm.service;

import leevgood.weekend_farm.domain.entity.product.Crops;
import leevgood.weekend_farm.repository.CropsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CropsService {

    private final CropsRepository cropsRepository;

    public List<Crops> getAllCrops(){
        return cropsRepository.findAll();
    }

    public Crops findById(Long cropsId){
        return cropsRepository.findById(cropsId)
                .orElseThrow(() -> new EntityNotFoundException("user not exist. id : " + cropsId));
    }
}
