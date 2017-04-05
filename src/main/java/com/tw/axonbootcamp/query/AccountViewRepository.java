package com.tw.axonbootcamp.query;

import com.tw.axonbootcamp.query.view.AccountView;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountViewRepository extends CrudRepository<AccountView, String> {

}
