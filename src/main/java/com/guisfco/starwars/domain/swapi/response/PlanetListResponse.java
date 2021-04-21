package com.guisfco.starwars.domain.swapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetListResponse implements Serializable {

    private static final long serialVersionUID = 5476098183001526060L;

    private List<PlanetDetail> results;
}
