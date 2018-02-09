package pe.edu.ltmj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.ltmj.domain.BankAccount;
import pe.edu.ltmj.repository.BankAccountRepository;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    @Qualifier("bankAccountRepositoryTransportImpl")
    BankAccountRepository repoTransport;

    @Autowired
    @Qualifier("bankAccountRepositoryRestHighImpl")
    BankAccountRepository repoRestHigh;
    
    @GetMapping
    public String sayHello() {
        return "Hello world";
    }

    @GetMapping("/banks")
    public List<BankAccount> getAllBankAccount() {
        return repoTransport.getAll();
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> getAllBankAccount2() {
        return repoRestHigh.getAll();
    }
}
