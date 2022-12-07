package com.tramdt.service.sercurity.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Date;
import com.google.api.services.people.v1.model.Person;
import com.tramdt.dto.TokenDto;
import com.tramdt.model.Account;
import com.tramdt.model.Role;
import com.tramdt.model.RoleName;
import com.tramdt.repository.sercurity.IAccountRepository;
import com.tramdt.service.ICustomerService;
import com.tramdt.service.sercurity.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    IAccountRepository accountRepository;

    @Autowired
    private ICustomerService customerService;


    @Override
    public Account findAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email).orElse(null);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Boolean existAccountByEmail(String email) {
        Account account = accountRepository.findAccountByEmail(email).orElse(null);
        if (account != null) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean existAccountByPhone(String phone) {
        Account account = accountRepository.findAccountByPhone(phone).orElse(null);
        if (account != null) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean existAccountByIdCard(String idCard) {
        Account account = accountRepository.findAccountByIdCard(idCard).orElse(null);
        if (account != null) {
            return true;
        }
        return false;
    }

    public Boolean existAccountByPassword(String password) {
        Account account = accountRepository.findAccountByPassword(password).orElse(null);
        System.out.println("Account" + account.getFullName());
        if (account != null) {
            return true;
        }
        return false;
    }

    @Override
    public void save(Account acc) {
        accountRepository.save(acc);
    }


}
