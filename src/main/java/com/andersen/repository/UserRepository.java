package com.andersen.repository;

import com.andersen.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
    void updateLastModified(Long userId);
}
