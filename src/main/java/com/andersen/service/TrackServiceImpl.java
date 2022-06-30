package com.andersen.service;

import com.andersen.dto.DtoMapper;
import com.andersen.dto.TrackDto;
import com.andersen.model.Track;
import com.andersen.model.User;
import com.andersen.repository.TrackRepository;
import com.andersen.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
//todo do we need to validate input data?
public class TrackServiceImpl implements TrackService {
    private final DtoMapper dtoMapper;
    private final TrackRepository trackRepository;
    private final UserRepository userRepository;

    @Override
    public TrackDto create(TrackDto trackDto) {
        Track track = dtoMapper.trackDtoToTrack(trackDto);
        User user = userRepository.getById(trackDto.getUserId());
        track.setUser(user);
        return dtoMapper.trackToTrackDto(trackRepository.save(track));
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
        return dtoMapper.trackListToTrackDtoList(
                trackRepository.findByUser(
                        userRepository.getById(userId)
                )
        );
    }
}
