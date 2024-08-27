package org.promo.data.services;

import org.promo.data.data.Eligibility;
import org.promo.data.data.EligibilityRepository;
import org.promo.data.data.Users;
import org.promo.data.data.UsersRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EligibilityService {

    private final EligibilityRepository eligibilityRepository;
    private final UsersRepository usersRepository;

    public EligibilityService(EligibilityRepository eligibilityRepository, UsersRepository usersRepoistory) {
        this.eligibilityRepository = eligibilityRepository;
        this.usersRepository = usersRepoistory;

    }

    public boolean userAlreadyRegistered(String msisdn, Integer promoId){
        boolean flag = false;

        Integer userId = usersRepository.getIdWithMsisdn(msisdn);
        if(userId != null){
            Optional<Eligibility> data = eligibilityRepository.getEligibilityWithUserId(userId);
            if (data.isPresent()){
                if (data.get().endTime().isAfter(LocalDateTime.now()) && data.get().endTime().isEqual(LocalDateTime.now()) && data.get().status().equals("Pending")){
                    //end_time has passed, a new one can be added
                    flag = true; //code 0
                }
            }
        }
        return flag;
    }

    public void addingEligibility(Users users, Integer promoId){
        try{
            Integer userId = usersRepository.getIdWithMsisdn(users.msisdn());
            LocalDateTime endTime = LocalDateTime.now().plusDays(14);

            eligibilityRepository.save(new Eligibility(null, userId, promoId, "Pending", LocalDateTime.now(), endTime, 2000, LocalDateTime.now()));
//            Optional<eligibility> pending = eligibilityRepository.insertEligibility(userId, promoId, "Pending", LocalDateTime.now(), endTime, 2000, LocalDateTime.now());
//            System.out.println(pending);
        }catch(Exception e){
            System.out.println("Error in adding eligibility " + e.getMessage());
        }

    }

    public Eligibility getEligibility(Integer userId){
        Optional<Eligibility> eligibility = eligibilityRepository.getEligibilityWithUserId(userId);
        return eligibility.orElse(null);
    }


}
