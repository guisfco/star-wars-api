package com.guisfco.starwars.fixture;

import com.guisfco.starwars.domain.dto.PlanetDto;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class PlanetDtoFixture {

    private final PlanetDto fixture = new PlanetDto();

    public static PlanetDtoFixture get() {
        return new PlanetDtoFixture();
    }

    public PlanetDto build() {
        return fixture;
    }

    public PlanetDtoFixture random() {

        fixture.setId(randomAlphanumeric(5));
        fixture.setClimate(randomAlphabetic(5));
        fixture.setName(randomAlphabetic(5));
        fixture.setTerrain(randomAlphabetic(5));

        return this;
    }
}
