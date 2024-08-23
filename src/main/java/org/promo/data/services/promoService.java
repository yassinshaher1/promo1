package org.promo.data.services;

import org.promo.data.data.promo;
import org.promo.data.data.promoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class promoService {

    private final promoRepository promoRepository;

    @Autowired
    public promoService(promoRepository promoRepository){
        this.promoRepository = promoRepository;
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
}
