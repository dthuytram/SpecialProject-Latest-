package com.tramdt.service.sercurity;

import com.tramdt.dto.TokenDto;
import com.tramdt.model.Account;
import com.tramdt.model.Customer;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAccountService {
    Account findAccountByEmail(String email);


    Account findByEmail(String email);

    Boolean existAccountByEmail(String email);
    Boolean existAccountByPhone(String phone);
    Boolean existAccountByIdCard(String idCard);

    Boolean existAccountByPassword(String password);
    void save(Account acc);

}
