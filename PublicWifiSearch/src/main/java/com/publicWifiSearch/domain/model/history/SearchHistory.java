package com.publicWifiSearch.domain.model.history;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class SearchHistory {
    private final Coordinate coordinate;
    private final String searchTime;

}
