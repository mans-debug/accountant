package com.andersen.service;

import com.andersen.dto.TrackDto;
import com.andersen.dto.TrackToSent;

import java.util.List;

public interface TrackService {

    TrackDto create(TrackToSent trackDto);

    TrackDto update(TrackDto trackDto);

    List<TrackDto> getByUser(Long userId);

    void remove(Long trackId);
}
