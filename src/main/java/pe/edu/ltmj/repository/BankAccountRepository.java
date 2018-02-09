package pe.edu.ltmj.repository;

import java.util.List;

import pe.edu.ltmj.domain.BankAccount;

public interface BankAccountRepository {

    List<BankAccount> getAll();
}
