package com.capco.teamcourse.db.repository;

import com.capco.teamcourse.db.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {


}

