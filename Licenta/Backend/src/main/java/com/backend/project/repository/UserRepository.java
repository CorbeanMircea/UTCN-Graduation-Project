package com.backend.project.repository;

import com.backend.project.model.Product;
import com.backend.project.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {

   User findByProductId(int productId);
   User findByUsername(String username);

}
