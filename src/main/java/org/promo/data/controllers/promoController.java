package org.promo.data.controllers;

import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promo")
public class promoController {

    @GetMapping("/eligible")
    public ResponseEntity<String> eligibleApi(@RequestParam("msisdn") String msisdn,
                                              @RequestParam("promoId") Integer promoId){

        return null;
    }

    @GetMapping("/prize")
    public ResponseEntity<String> prizeApi(@RequestParam("msisdn") String msisdn,
                                           @RequestParam("promoId")Integer promoId,
                                           @RequestParam("dataConsumedInMb") Integer dataConsumedInMb){

        return null;
    }
}
