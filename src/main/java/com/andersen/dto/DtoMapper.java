package com.andersen.dto;

import com.andersen.model.Track;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface DtoMapper {
    TrackDto trackToTrackDto(Track track);

    Track trackDtoToTrack(TrackDto trackDto);

    @Mapping(source = "description", target = "text")
    @Mapping(source = "spentHours", target = "timeSpent")
    Track trackToSentToTrack(TrackToSent track);

    List<TrackDto> trackListToTrackDtoList(List<Track> tracks);
}
