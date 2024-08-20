package org.promo.data.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface promoRepository extends CrudRepository {// add the <promo, String>
}

