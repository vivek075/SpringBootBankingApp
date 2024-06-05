package com.example.bankingapp;

import com.example.bankingapp.model.Account;
import com.example.bankingapp.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankingAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankingAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(AccountRepository accountRepository) {
        return args -> {
            // Create an initial account with a default balance
            Account account = new Account();
            account.setBalance(1000.00); // Set a default balance
            accountRepository.save(account);
        };
    }
}
