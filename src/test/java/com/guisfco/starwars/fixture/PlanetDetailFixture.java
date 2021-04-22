package com.guisfco.starwars.fixture;

import com.guisfco.starwars.domain.swapi.PlanetDetail;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class PlanetDetailFixture {

    private final PlanetDetail fixture = new PlanetDetail();

    public static PlanetDetailFixture get() {
        return new PlanetDetailFixture();
    }

    public PlanetDetail build() {
        return fixture;
    }

    public List<PlanetDetail> randomList() {

        return IntStream.range(0, nextInt(1, 3))
                .mapToObj(i -> PlanetDetailFixture.get().random().build())
                .collect(Collectors.toList());
    }

    private List<String> randomFilms() {

        return IntStream.range(0, nextInt(1, 10))
                .mapToObj(i -> randomAlphabetic(5))
                .collect(Collectors.toList());
    }

    public PlanetDetailFixture random() {

        fixture.setFilms(randomFilms());
        fixture.setClimate(randomAlphabetic(5));
        fixture.setName(randomAlphabetic(5));
        fixture.setTerrain(randomAlphabetic(5));

        return this;
    }
}
