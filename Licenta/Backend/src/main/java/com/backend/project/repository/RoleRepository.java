package com.backend.project.repository;


import com.backend.project.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role,Integer> {
    Role findByRole(String role);
}
