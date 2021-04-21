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
public class PlanetDetail implements Serializable {

    private static final long serialVersionUID = -8286628071720329889L;

    private String name;

    private String climate;

    private String terrain;

    private List<String> films;
}
