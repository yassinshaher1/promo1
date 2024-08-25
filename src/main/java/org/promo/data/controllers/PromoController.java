package org.promo.data.controllers;

import org.promo.data.data.*;
import org.promo.data.services.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.promo.data.services.EligibilityService;
import org.promo.data.services.UsersService;


import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/promo")
public class PromoController {

    private final EligibilityService eligibilityService;
    private final UsersService usersService;
    private final PromoService promoService;
    private final PrizeRepository prizeRepository;
    private final PromoRepository promoRepository;
    private final UsersRepository usersRepository;
    private final EligibilityRepository eligibilityRepository;

    @Autowired
    public PromoController(EligibilityService eligibilityService,
                           UsersService usersService,
                           PromoService promoService,
                           PrizeRepository prizeRepository,
                           PromoRepository promoRepository,
                           UsersRepository usersRepository, EligibilityRepository eligibilityRepository){

        this.eligibilityService = eligibilityService;
        this.usersService = usersService;
        this.promoService = promoService;
        this.prizeRepository = prizeRepository;
        this.promoRepository = promoRepository;
        this.usersRepository = usersRepository;
        this.eligibilityRepository = eligibilityRepository;
    }

    @GetMapping("/eligible")
    public ResponseEntity<String> eligibleApi(@RequestParam("msisdn") String msisdn,
                                              @RequestParam("promoId") Integer promoId){
        Users newUser = new Users(null, msisdn, LocalDateTime.now());
        try {
            usersService.checkUserPresent(newUser);

            boolean eligible = eligibilityService.userAlreadyRegistered(msisdn, promoId);
            if (!eligible) {

                eligibilityService.addingEligibility(newUser, promoId);
                return ResponseEntity.ok("User successfully registered for the promo");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is already registered for the promo");
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("Error in database " + e.getMessage()));
        }
    }

    @GetMapping("/prize")
    public ResponseEntity<String> prizeApi(@RequestParam("msisdn") String msisdn,
                                           @RequestParam("promoId")Integer promoId,
                                           @RequestParam("dataConsumedInMb") Integer dataConsumedInMb){

        try {


            Promo promo = promoService.getPromo(promoId);
            Users user = usersService.getUser(msisdn);
            Integer userId = usersRepository.getIdWithMsisdn(user.msisdn());
            Eligibility eligibility = eligibilityService.getEligibility(userId);
//        Integer eligibilityId = eligibilityRepository.latestEligibilityIdByUserId(userId);


            if (promoService.checkPromoPresent(promo)) {
                if (promoService.checkIfRequirmentsMet(promoId, user, dataConsumedInMb, eligibility)) {
                    return ResponseEntity.ok("Prize granted");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Prize lost");
                }
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promo not found");
            }

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("Error in database " + e.getMessage()));
        }

    }
}
