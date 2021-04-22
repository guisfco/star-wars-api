package com.guisfco.starwars.fixture;

import com.guisfco.starwars.domain.dto.PlanetDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class PlanetDtoFixture {

    private final PlanetDto fixture = new PlanetDto();

    public static PlanetDtoFixture get() {
        return new PlanetDtoFixture();
    }

    public PlanetDto build() {
        return fixture;
    }

    public List<PlanetDto> randomList() {

        return IntStream.range(0, nextInt(1, 3))
                .mapToObj(i -> PlanetDtoFixture.get().random().build())
                .collect(Collectors.toList());
    }

    public PlanetDtoFixture random() {

        fixture.setId(randomAlphanumeric(5));
        fixture.setClimate(randomAlphabetic(5));
        fixture.setName(randomAlphabetic(5));
        fixture.setTerrain(randomAlphabetic(5));

        return this;
    }
}
