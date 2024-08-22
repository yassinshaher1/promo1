package org.promo.data.services;

import org.promo.data.data.users;
import org.promo.data.data.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class usersService {

    private final usersRepository usersRepository;

    @Autowired
    public usersService(usersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public void checkUserPresent(users users){
        Optional<users> existingUser = usersRepository.selectByMsisdn((users.msisdn()));

        if (existingUser.isPresent()){
        }else{
            usersRepository.insertUser(users.msisdn(), LocalDateTime.now());
        }
    }



}
