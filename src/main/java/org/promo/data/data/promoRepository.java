package org.promo.data.data;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface promoRepository extends CrudRepository<promo, String> {// add the <promo, String>

    @Query("SELECT * FROM promo WHERE promo_id = :promo_id")
    Optional<promo> selectByPromoId (Integer promoId);


}

