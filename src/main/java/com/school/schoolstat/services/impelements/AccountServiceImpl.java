package com.school.schoolstat.services.impelements;

import com.school.schoolstat.dao.AccountRepository;
import com.school.schoolstat.models.entities.Account;
import com.school.schoolstat.services.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Account loadUserByUsername(String username) {
        return accountRepository.findByEmailOrPhone(username);
    }

    @Override
    public Long countAllAccounts() {
        return accountRepository.count();
    }

}
