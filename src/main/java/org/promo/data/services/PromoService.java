package org.promo.data.services;

import org.promo.data.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class PromoService {

    private final PromoRepository promoRepository;
    private final UsersRepository usersRepository;
    private final EligibilityRepository eligibilityRepository;
    private final PrizeRepository prizeRepository;


    @Autowired
    public PromoService(PromoRepository promoRepository,
                        UsersRepository usersRepository,
                        EligibilityRepository eligibilityRepository,
                        PrizeRepository prizeRepository){

        this.promoRepository = promoRepository;
        this.usersRepository = usersRepository;
        this.eligibilityRepository = eligibilityRepository;
        this.prizeRepository = prizeRepository;
    }

    public boolean checkPromoPresent(Promo promo){
        Optional<Promo> existingPromo = promoRepository.selectByPromoId(promo.promoId());

        return existingPromo.isPresent();
//        if ( existingPromo.isPresent()){
//            return true; //code 3 promo available
//        }else{
//            return false; // code 2
//        }
    }

    public Promo getPromo(int promoId){
        Optional<Promo> promo = promoRepository.selectByPromoId(promoId);
        return promo.orElse(null);
    }



    public boolean checkIfRequirmentsMet(Integer promoId, Users users, Integer dataConsumedInMb, Eligibility eligibility){
        try {
            Integer userId = usersRepository.getIdWithMsisdn(users.msisdn());
            Integer dataRequired = eligibilityRepository.dataConsumedByPromoId(promoId);
//            Integer eligibilityId = eligibilityRepository.latestEligibilityIdByUserId(userId);
            LocalDateTime endTime = eligibility.endTime();

            if (LocalDateTime.now().isAfter(endTime) && dataConsumedInMb >= dataRequired){
                prizeRepository.save(new Prize(null, eligibility.eligibilityId(), "Granted", LocalDateTime.now()));
//                eligibilityRepository.updateEligibilityStatus("Done", eligibilityId);

                eligibilityRepository.save(Eligibility.UpdateStatus(eligibility,"Done"));
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
