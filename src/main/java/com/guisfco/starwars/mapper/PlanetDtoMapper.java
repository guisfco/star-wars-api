package com.guisfco.starwars.mapper;

import com.guisfco.starwars.domain.dto.PlanetDto;
import com.guisfco.starwars.entity.Planet;

import static java.util.Objects.isNull;

public class PlanetDtoMapper {

    public static PlanetDto apply(final Planet planet) {

        if (isNull(planet)) {
            return null;
        }

        return PlanetDto.builder()
                .climate(planet.getClimate())
                .filmsAppearances(planet.getFilmsAppearances())
                .id(planet.getId())
                .name(planet.getName())
                .terrain(planet.getTerrain())
                .build();
    }
}
