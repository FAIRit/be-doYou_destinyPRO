package com.github.fairit.destinypro.service.impl.definition;

import com.github.fairit.destinypro.entity.RaceEntity;
import com.github.fairit.destinypro.repository.RaceRepository;
import com.github.fairit.destinypro.service.definition.DefinitionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RaceDefinitionService implements DefinitionService {

    private final RaceDefinitionApiServiceImpl raceApiService;
    private final RaceRepository raceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RaceDefinitionService(final RaceDefinitionApiServiceImpl raceApiService, final RaceRepository raceRepository,
                                 final ModelMapper modelMapper) {
        this.raceApiService = raceApiService;
        this.raceRepository = raceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void saveDefinitionInDB() {
        raceRepository.saveAll(getRaceEntities());
    }

    private List<RaceEntity> getRaceEntities() {
        var raceEntityList = new ArrayList<RaceEntity>();

        for (final var raceSpecificApi : raceApiService.getListOfDefinition()) {
            var entity = modelMapper.map(raceSpecificApi, RaceEntity.class);
            raceEntityList.add(entity);
        }
        return raceEntityList;
    }
}
