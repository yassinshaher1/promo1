package org.promo.data.services;

import org.promo.data.data.Promo;
import org.promo.data.data.Users;
import org.promo.data.data.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public void checkUserPresent(Users users){
        Optional<Users> existingUser = usersRepository.selectByMsisdn((users.msisdn()));

        if (existingUser.isPresent()){
        }else{
//            usersRepository.save(new users(null, users.msisdn(), LocalDateTime.now()));
            usersRepository.insertUser(users.msisdn(), LocalDateTime.now());
        }
    }

    public Users getUser(String msisdn){
        Optional<Users> User = usersRepository.selectByMsisdn(msisdn);
        return User.orElse(null);
    }
}
