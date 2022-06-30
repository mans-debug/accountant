package com.andersen.service;

import com.andersen.dto.TrackDto;

import java.util.List;

public interface TrackService {

    TrackDto create(TrackDto trackDto);

    TrackDto update(TrackDto trackDto);

    List<TrackDto> getByUser(Long userId);
}
