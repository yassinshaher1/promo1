package org.promo.data.controllers;

import org.promo.data.data.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.promo.data.services.eligibilityService;
import org.promo.data.services.usersService;


import java.time.LocalDateTime;

@RestController
@RequestMapping("/promo")
public class promoController {

    private final eligibilityService eligibilityService;
    private final usersService usersService;

    @Autowired
    public promoController(eligibilityService eligibilityService,usersService usersService){
        this.eligibilityService = eligibilityService;
        this.usersService = usersService;
    }

    @GetMapping("/eligible")
    public ResponseEntity<String> eligibleApi(@RequestParam("msisdn") String msisdn,
                                              @RequestParam("promoId") Integer promoId){
        users newuser = new users(null, msisdn, LocalDateTime.now());
        try {
            usersService.checkUserPresent(newuser);
            boolean eligible = eligibilityService.userAlreadyRegistered(msisdn, promoId);
            if (!eligible) {
                eligibilityService.addingEligibility(newuser, promoId);
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

        return null;
    }
}
