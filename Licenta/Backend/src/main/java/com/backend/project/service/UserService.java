package com.backend.project.service;

import com.backend.project.repository.UserRepository;
import com.backend.project.model.DatabaseSequence;
import com.backend.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MongoOperations mongoOperations;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundedUser = userRepository.findByUsername(username);
        if(foundedUser == null){
            return null;
        }
        String name = foundedUser.getUsername();
        String pwd = foundedUser.getPassword();

        return new org.springframework.security.core.userdetails.User(name,pwd,new ArrayList<>());
    }


      public int getSequence(String sequenceName){
        Query query = new Query(Criteria.where("_id").is(sequenceName));
        Update update = new Update().inc("seq",1);
        DatabaseSequence counter = mongoOperations.findAndModify(query,update,options().returnNew(true).upsert(true),DatabaseSequence.class);

        return !Objects.isNull(counter) ? counter.getSeq() : 1;

    }

    public String generateEventCode() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }


}
