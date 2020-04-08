package com.github.fairit.destinypro.serviceimpl.definition;

import com.github.fairit.destinypro.entity.api.characterrace.RaceApi;
import com.github.fairit.destinypro.entity.api.characterrace.RaceSpecificApi;
import com.github.fairit.destinypro.service.definition.DefinitionApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RaceDefinitionApiServiceImpl implements DefinitionApiService {

    private final RestTemplate restTemplate;
    private final DefinitionsUrlApiService definitionsUrlApiService;

    @Autowired
    public RaceDefinitionApiServiceImpl(final RestTemplate restTemplate, final DefinitionsUrlApiService definitionsUrlApiService) {
        this.restTemplate = restTemplate;
        this.definitionsUrlApiService = definitionsUrlApiService;
    }

    public List<RaceSpecificApi> getListOfDefinition() {
        RaceApi raceApiObject = restTemplate
                .getForObject(definitionsUrlApiService.getRaceApiAddress(), RaceApi.class);

        List<RaceSpecificApi> listOfRaceDefinition = new ArrayList<>();
        if (raceApiObject != null) {
            listOfRaceDefinition.add(raceApiObject.getAwoken());
            listOfRaceDefinition.add(raceApiObject.getExo());
            listOfRaceDefinition.add(raceApiObject.getHuman());
        }
        return listOfRaceDefinition;
    }
}