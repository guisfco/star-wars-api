package com.guisfco.starwars.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentPageResponse<T> implements Serializable {

    private static final long serialVersionUID = -6643949362437081992L;

    private long totalElements;

    private int totalPages;

    private boolean first;

    private boolean last;

    private List<T> content;
}
