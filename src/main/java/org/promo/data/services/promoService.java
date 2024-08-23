package org.promo.data.services;

import org.promo.data.data.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class promoService {

    private final promoRepository promoRepository;
    private final usersRepository usersRepository;
    private final eligibilityRepository eligibilityRepository;
    private final prizeRepository prizeRepository;


    @Autowired
    public promoService(promoRepository promoRepository,
                        usersRepository usersRepository,
                        eligibilityRepository eligibilityRepository,
                        prizeRepository prizeRepository){

        this.promoRepository = promoRepository;
        this.usersRepository = usersRepository;
        this.eligibilityRepository = eligibilityRepository;
        this.prizeRepository = prizeRepository;
    }

    public boolean checkPromoPresent(promo promo){
        Optional<promo> existingPromo = promoRepository.selectByPromoId(promo.promoId());

        return existingPromo.isPresent();
//        if ( existingPromo.isPresent()){
//            return true;
//        }else{
//            return false; // code 2
//        }
    }

    public boolean checkIfRequirmentsMet(Integer promoId, users users, Integer dataConsumedInMb, eligibility eligibility){
        try {
            Integer userId = usersRepository.getIdWithMsisdn(users.msisdn());
            Integer dataRequired = eligibilityRepository.dataConsumedByPromoId(promoId);
            Integer eligibilityId = eligibilityRepository.latestEligibilityIdByUserId(userId);
            LocalDateTime endTime = eligibility.endTime();

            if (LocalDateTime.now().isAfter(endTime) && dataConsumedInMb >= dataRequired){
                prizeRepository.insertPrize(eligibilityId, "Granted", LocalDateTime.now());
                eligibilityRepository.updateEligibilityStatus("Done", eligibilityId);
                return true;// code 4

            }else{
                return false; //code 5
            }
        }catch(Exception e){
            System.out.println("Error in handling step 3 " + e.getMessage());
            return false;
        }
    }
}
