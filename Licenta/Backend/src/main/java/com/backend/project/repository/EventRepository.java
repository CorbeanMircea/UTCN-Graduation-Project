package com.backend.project.repository;

import com.backend.project.model.Event;
import com.backend.project.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event,Integer> {
    Event findEventByEventCode(String eventCode);
    Event findEventByName(String name);
    void deleteByEventCode(String eventCode);
}
