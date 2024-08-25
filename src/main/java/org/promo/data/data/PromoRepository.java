package org.promo.data.data;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromoRepository extends CrudRepository<Promo, String> {// add the <promo, String>

    @Query("SELECT * FROM promo WHERE promo_id = :promoId")
    Optional<Promo> selectByPromoId (Integer promoId);


}

