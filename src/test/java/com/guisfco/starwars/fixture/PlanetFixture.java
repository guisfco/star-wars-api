package com.guisfco.starwars.fixture;

import com.guisfco.starwars.entity.Planet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class PlanetFixture {

    private final Planet fixture = new Planet();

    public static PlanetFixture get() {
        return new PlanetFixture();
    }

    public Planet build() {
        return fixture;
    }

    public List<Planet> randomList() {

        return IntStream.range(0, nextInt(1, 3))
                .mapToObj(i -> PlanetFixture.get().random().build())
                .collect(Collectors.toList());
    }

    public PlanetFixture random() {

        fixture.setId(randomAlphanumeric(5));
        fixture.setClimate(randomAlphabetic(5));
        fixture.setName(randomAlphabetic(5));
        fixture.setTerrain(randomAlphabetic(5));

        return this;
    }
}
