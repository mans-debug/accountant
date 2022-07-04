package com.andersen.service;

import com.andersen.dto.DtoMapper;
import com.andersen.dto.TrackDto;
import com.andersen.dto.TrackToSent;
import com.andersen.model.Track;
import com.andersen.model.User;
import com.andersen.repository.TrackRepository;
import com.andersen.repository.UserRepository;
import jakarta.ejb.NoSuchEntityException;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
//todo do we need to validate input data?
public class TrackServiceImpl implements TrackService {
    private final DtoMapper dtoMapper;
    private final TrackRepository trackRepository;
    private final UserRepository userRepository;

    @Override
    public TrackDto create(TrackDto trackDto) {
        Track track = trackDtoToTrack(trackDto);
        Optional<User> user = userRepository.findById(trackDto.getUserId());
        if(user.isPresent()) {
            track.setUser(user.get());
            userRepository.updateLastModified(user.get().getTelegramId());
            return dtoMapper.trackToTrackDto(trackRepository.save(track));
        }
        throw new NoSuchEntityException();
    }

    @Override
    public TrackDto update(TrackDto trackDto) {
        return dtoMapper.trackToTrackDto(
                trackRepository.save(
                        dtoMapper.trackDtoToTrack(trackDto)
                )
        );
    }

    @Override
    public List<TrackDto> getByUser(Long userId) {
        return trackListToTrackDtoList(
                trackRepository.findByUser(
                        userRepository.getById(userId)
                )
        );
    }

    @Override
    public void remove(Long trackId) {
        trackRepository.deleteById(trackId);
    }

    public Track trackDtoToTrack(TrackDto trackDto) {
        if ( trackDto == null ) {
            return null;
        }

        Track.TrackBuilder track = Track.builder();

        track.text( trackDto.getDescription() );
        track.timeSpent( trackDto.getSpentHours() );
        track.id( trackDto.getId() );

        return track.build();
    }

    public TrackDto trackToTrackDto(Track track) {
        TrackDto.TrackDtoBuilder trackDto = TrackDto.builder();

        trackDto.description( track.getText() );
        trackDto.spentHours( track.getTimeSpent() );
        trackDto.id( track.getId() );

        return trackDto.build();
    }
    public List<TrackDto> trackListToTrackDtoList(List<Track> tracks) {
        List<TrackDto> list = new ArrayList<TrackDto>( tracks.size() );
        for ( Track track : tracks ) {
            list.add( trackToTrackDto( track ) );
        }
        return list;
    }
}
