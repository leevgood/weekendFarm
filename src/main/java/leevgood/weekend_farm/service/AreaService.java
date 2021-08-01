package leevgood.weekend_farm.service;

import leevgood.weekend_farm.domain.entity.Area;
import leevgood.weekend_farm.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaService {
    private final AreaRepository areaRepository;

    public List<Area> findAll(){
        return areaRepository.findAll();
    }
}
