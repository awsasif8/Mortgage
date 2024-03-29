package com.ing.util;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ing.entity.Account;
import com.ing.entity.Mortagage;
import com.ing.entity.Transaction;
import com.ing.exception.MortagageManagementException;
import com.ing.repository.AccountRepository;
import com.ing.repository.MortagageRepository;
import com.ing.repository.TransactionRepository;
@Component
public class MortgageBalanceDeduct {
    @Autowired
    MortagageRepository mortagageRepository;
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    TransactionRepository transactionRepository;
    
      
@Scheduled(fixedDelay = 60000)
    public void deductBalance()
    {
        List<Mortagage> mortagageList = mortagageRepository.findAll();
        
     
        
        for(int i =0;i<mortagageList.size();i++)
        {
            String customerIdMortagage = mortagageList.get(i).getCustomerId();
            
            Account accountData = accountRepository.findBycustomerId(customerIdMortagage);
            
            if(accountData.getAccountNumber() != null)
            {
                String accountNumber = accountData.getAccountNumber();
                String customerId = accountData.getCustomerId();
                Account account = new Account();
                account.setAccountNumber(accountData.getAccountNumber());
                Double accountDegrade = accountData.getBalance()-200;
                account.setBalance(accountDegrade);
                account.setAccountType(accountData.getAccountType());
                account.setCustomerId(accountData.getCustomerId());
                account.setCreatedOn(accountData.getCreatedOn());
                accountRepository.save(account);
                
                Transaction transactionData = new Transaction();
                transactionData.setAccountNumber(accountNumber);
                transactionData.setAccountType("mortgage");
                transactionData.setDescription("payment towards to mortgage loan");
                transactionData.setTransactionAmount(200);
                transactionData.setTransactionType("debit");
                transactionData.setTransactionDate(LocalDateTime.now());
                transactionRepository.save(transactionData);
                
               
                
                Mortagage mort = mortagageRepository.findBycustomerId(customerId);
                Mortagage mortagageData = new Mortagage();
                mortagageData.setMortagageId(mort.getMortagageId());
                mortagageData.setCustomerId(mort.getCustomerId());
                Double mortgageMinus = mort.getMortagageBalance() + 200;
                mortagageData.setMortagageBalance(mortgageMinus);
                mortagageData.setDeposit(mort.getDeposit());
                mortagageData.setMortagageType(mort.getMortagageType());
                mortagageData.setPropertyCost(mort.getPropertyCost());
                mortagageRepository.save(mortagageData);
                
                Transaction transactionDataDebit = new Transaction();
                transactionDataDebit.setAccountNumber(mort.getMortagageId());
                transactionDataDebit.setAccountType("transaction");
                transactionDataDebit.setDescription("release of payment");
                transactionDataDebit.setTransactionAmount(200);
                transactionDataDebit.setTransactionType("credit");
                transactionDataDebit.setTransactionDate(LocalDateTime.now());
                transactionRepository.save(transactionDataDebit);
            }
            else
            {
                throw new MortagageManagementException("exception in account number");
            }
        }
    }
    
}
