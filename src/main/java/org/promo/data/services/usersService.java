package org.promo.data.services;

import org.promo.data.data.users;
import org.promo.data.data.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class usersService {

    private final usersRepository usersRepository;

    @Autowired
    public usersService(usersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public boolean checkUserpresent(users users){
        Optional<users> existingUser = usersRepository.selectByMsisdn((users.msisdn()));

        if (existingUser.isPresent()){
            return true;
        }else{
            return false;
        }
    }



}
