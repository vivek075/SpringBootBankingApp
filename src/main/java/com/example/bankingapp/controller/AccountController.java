package com.example.bankingapp.controller;

import com.example.bankingapp.model.Account;
import com.example.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("account", new Account());
        return "index";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam Long id, @RequestParam double amount, Model model) {
        try {
            Account account = accountService.deposit(id, amount);
            model.addAttribute("account", account);
            model.addAttribute("message", "Deposit successful");
        } catch (IllegalArgumentException e) {
            model.addAttribute("account", new Account());
            model.addAttribute("message", e.getMessage());
        }
        return "result";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam Long id, @RequestParam double amount, Model model) {
        try {
            Account account = accountService.withdraw(id, amount);
            model.addAttribute("account", account);
            model.addAttribute("message", "Withdraw successful");
        } catch (IllegalArgumentException e) {
            model.addAttribute("account", new Account());
            model.addAttribute("message", e.getMessage());
        }
        return "result";
    }

    @PostMapping("/createAccount")
    public String createAccount(Model model) {
        Account account = new Account();
        account.setBalance(0.00); // Set initial balance to 0
        account = accountService.save(account);
        model.addAttribute("account", account);
        model.addAttribute("message", "Account created successfully");
        return "result";
    }
}
