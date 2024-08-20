package org.promo.data.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface notificationRepository extends CrudRepository {// add the <notification, String>
}
