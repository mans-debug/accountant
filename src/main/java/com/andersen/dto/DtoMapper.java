package com.andersen.dto;

import com.andersen.model.Track;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DtoMapper {
    TrackDto trackToTrackDto(Track track);

    Track trackDtoToTrack(TrackDto trackDto);

    List<TrackDto> trackListToTrackDtoList(List<Track> tracks);
}
