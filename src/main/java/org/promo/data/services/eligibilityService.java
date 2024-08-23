package org.promo.data.services;

import org.promo.data.data.eligibility;
import org.promo.data.data.eligibilityRepository;
import org.promo.data.data.users;
import org.promo.data.data.usersRepository;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

public class eligibilityService {

    private final eligibilityRepository eligibilityRepository;
    private final usersRepository usersRepository;

    public eligibilityService(eligibilityRepository eligibilityRepository, usersRepository usersRepoistory) {
        this.eligibilityRepository = eligibilityRepository;
        this.usersRepository = usersRepoistory;

    }

    public boolean userAlreadyRegistered(eligibility eligibility, users users){
        Optional<eligibility> data = eligibilityRepository.getEligibilityWithUserId((usersRepository.getIdWithMsisdn(users.msisdn())));

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
            return false;// code
        }
    }

    public void addingEligibility(users users, Integer promoId){
        try{
            Integer userId = usersRepository.getIdWithMsisdn(users.msisdn());
            LocalDateTime endTime = LocalDateTime.now().plusDays(14);

            eligibilityRepository.insertEligibility(userId, promoId, "Pending", LocalDateTime.now(), endTime, 2000, LocalDateTime.now());
        }catch(Exception e){
            System.out.println("Error in adding eligibility " + e.getMessage());
        }

    }


}
