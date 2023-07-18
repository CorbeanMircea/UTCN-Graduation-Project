package com.backend.project.repository;

import com.backend.project.model.ContactMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<ContactMessage,Integer> {
}
