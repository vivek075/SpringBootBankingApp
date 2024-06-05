package com.example.bankingapp.service;

import com.example.bankingapp.model.Account;
import com.example.bankingapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    @Transactional
    public Account deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }

    @Transactional
    public Account withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }

    @Transactional
    public Account save(Account account) {
        return accountRepository.save(account);
    }
}
