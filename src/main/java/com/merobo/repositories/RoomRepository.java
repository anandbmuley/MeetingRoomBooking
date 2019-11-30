package com.merobo.repositories;

import com.merobo.beans.RoomBean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<RoomBean, String> {
}
