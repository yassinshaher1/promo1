package org.promo.data.services;

import org.promo.data.data.eligibility;
import org.promo.data.data.eligibilityRepository;
import org.promo.data.data.users;
import org.promo.data.data.usersRepository;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class eligibilityService {

    private final eligibilityRepository eligibilityRepository;
    private final usersRepository usersRepository;

    public eligibilityService(eligibilityRepository eligibilityRepository, usersRepository usersRepoistory) {
        this.eligibilityRepository = eligibilityRepository;
        this.usersRepository = usersRepoistory;

    }

    public boolean userAlreadyRegistered(String msisdn, Integer promoId){
        Integer userId = usersRepository.getIdWithMsisdn(msisdn);
        if(userId != null){
            Optional<eligibility> data = eligibilityRepository.getEligibilityWithUserId(userId);
            if (data.isPresent()){
                if (data.get().endTime().isBefore(LocalDateTime.now()) && data.get().status().equals("Ended")){
                    //end_time has passed, a new one can be added
                    return false; //code 1
                }else{
                    // end_time
                    return true; //code 0
                }
            }else{
                // not in the database
                return false;// code 1
            }
        }else{
            return false;
        }
    }

    public void addingEligibility(users users, Integer promoId){
        try{
            Integer userId = usersRepository.getIdWithMsisdn(users.msisdn());
            LocalDateTime endTime = LocalDateTime.now().plusDays(14);

            eligibilityRepository.insertEligibility(promoId, "Pending", LocalDateTime.now(), endTime, 2000, LocalDateTime.now());
        }catch(Exception e){
            System.out.println("Error in adding eligibility " + e.getMessage());
        }

    }


}
