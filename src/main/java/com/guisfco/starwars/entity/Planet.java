package com.guisfco.starwars.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Planet implements Serializable {

    private static final long serialVersionUID = 2334580526128442487L;

    @Id
    private String id;

    private String name;

    private String climate;

    private String terrain;
}
