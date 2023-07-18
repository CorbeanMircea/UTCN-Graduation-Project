package com.backend.project.repository;

import com.backend.project.model.Donation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DonationRepository extends MongoRepository<Donation,Integer> {
}
