package com.guisfco.starwars.fixture;

import com.guisfco.starwars.domain.request.PlanetRequest;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class PlanetRequestFixture {

    private final PlanetRequest fixture = new PlanetRequest();

    public static PlanetRequestFixture get() {
        return new PlanetRequestFixture();
    }

    public PlanetRequest build() {
        return fixture;
    }

    public PlanetRequestFixture climate(final String climate) {
        fixture.setClimate(climate);
        return this;
    }

    public PlanetRequestFixture name(final String name) {
        fixture.setName(name);
        return this;
    }

    public PlanetRequestFixture terrain(final String terrain) {
        fixture.setTerrain(terrain);
        return this;
    }

    public PlanetRequestFixture random() {

        fixture.setClimate(randomAlphabetic(5));
        fixture.setName(randomAlphabetic(5));
        fixture.setTerrain(randomAlphabetic(5));

        return this;
    }
}
