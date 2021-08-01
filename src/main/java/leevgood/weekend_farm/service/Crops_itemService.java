package leevgood.weekend_farm.service;

import leevgood.weekend_farm.domain.entity.CropsItem;
import leevgood.weekend_farm.repository.Crops_itemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Crops_itemService {

    private final Crops_itemRepository crops_itemRepository;

    public List<CropsItem> findAll(){
        return crops_itemRepository.findAll();
    }

    public Optional<CropsItem> findById(Long id){
        return crops_itemRepository.findById(id);
    }
}
