package org.promo.data.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface prizeRepository extends CrudRepository {// add the <prize, String>
}

