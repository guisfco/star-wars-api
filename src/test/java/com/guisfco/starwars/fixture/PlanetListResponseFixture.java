package com.guisfco.starwars.fixture;

import com.guisfco.starwars.domain.swapi.PlanetDetail;
import com.guisfco.starwars.domain.swapi.response.PlanetListResponse;

import java.util.List;

public class PlanetListResponseFixture {

    private final PlanetListResponse fixture = new PlanetListResponse();

    public static PlanetListResponseFixture get() {
        return new PlanetListResponseFixture();
    }

    public PlanetListResponse build() {
        return fixture;
    }

    public PlanetListResponseFixture results(final List<PlanetDetail> results) {
        fixture.setResults(results);
        return this;
    }

    public PlanetListResponseFixture random() {

        fixture.setResults(PlanetDetailFixture.get().randomList());

        return this;
    }
}
