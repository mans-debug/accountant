package com.andersen.repository;

import com.andersen.model.Track;
import com.andersen.model.User;

import java.util.List;

public interface TrackRepository extends CrudRepository<Track, Long>{
    List<Track> findByUser(User user);
}
