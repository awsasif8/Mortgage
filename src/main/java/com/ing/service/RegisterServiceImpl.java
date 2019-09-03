package com.ing.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.dto.RegisterRequestDTO;
import com.ing.dto.RegisterResponseDTO;
import com.ing.entity.Account;
import com.ing.entity.Customer;
import com.ing.entity.Mortagage;
import com.ing.exception.MortagageManagementException;
import com.ing.repository.AccountRepository;
import com.ing.repository.CustomerRepository;
import com.ing.repository.MortagageRepository;
import com.ing.util.MailWithOTPService;

/**
 * Class for registering customer
 * 
 * @author User1
 *
 */
@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MortagageRepository mortagageRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	MailWithOTPService mailWithOTPService;

	private static final Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);

	/**
	 * Method for registering customer
	 * 
	 * @param requestDTO
	 * @return registerResponseDTO
	 * 
	 */
	@Override
	public RegisterResponseDTO registerCustomer(RegisterRequestDTO requestDTO) {

		LocalDate today = LocalDate.now();
		Period period = Period.between(requestDTO.getDob(), today);
		if (period.getYears() < 18)
			throw new MortagageManagementException("Sorry we are unable to grant you the mortgage at this moment");
		Random rand = null;
		try {
			rand = SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e) {
			throw new MortagageManagementException("Unable to generate account number");

		}
		String customerIdPrefix = "ING";
		int n = rand.nextInt(99999);
		String customerId = customerIdPrefix + n;
		int m = rand.nextInt(999999);
		String mortgageIdPrefix = "MORT";
		String mortgageId = mortgageIdPrefix + m;
		String password = requestDTO.getFirstName() + "@" + requestDTO.getDob().getYear();
		int o = rand.nextInt(9999);
		String accountIdIdPrefix = "MAGGIE";
		String accountId = accountIdIdPrefix + o;
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setDob(requestDTO.getDob());
		customer.setEmail(requestDTO.getEmail());
		customer.setFirstName(requestDTO.getFirstName());
		customer.setLastName(requestDTO.getLastName());
		customer.setMobile(requestDTO.getMobile());
		customer.setOccupation(requestDTO.getOccupation());
		customer.setPassword(password);
		customerRepository.save(customer);

		Account account = new Account();
		account.setAccountNumber(accountId);
		account.setBalance(requestDTO.getPropertyCost() - requestDTO.getDeposit());
		account.setCreatedOn(LocalDate.now());
		account.setCustomerId(customer.getCustomerId());
		account.setAccountType("Transaction");
		accountRepository.save(account);

		Mortagage mortgage = new Mortagage();
		mortgage.setCustomerId(customer.getCustomerId());
		mortgage.setDeposit(requestDTO.getDeposit());
		mortgage.setMortagageId(mortgageId);
		mortgage.setPropertyCost(requestDTO.getPropertyCost());
		mortgage.setMortagageType("Mortgage");
		mortgage.setMortagageBalance(-(requestDTO.getPropertyCost() - requestDTO.getDeposit()));
		mortagageRepository.save(mortgage);

		RegisterResponseDTO result = new RegisterResponseDTO();
		result.setCustomerId(customer.getCustomerId());
		result.setMessage("Registration Successfull");
		result.setStatusCode(201);
		result.setStatus("SUCCESS");

		
		result.setTransactionAcc(account.getAccountNumber());
		result.setMortgageAcc(mortgage.getMortagageId());
		result.setPassword(password);
		generateOTPandSendMail(requestDTO.getEmail(),result);
		return result;
	}

	/**
	 * Method to generate email
	 * 
	 * @param email
	 * @return status
	 */
	public void generateOTPandSendMail(String email,RegisterResponseDTO reg) {

		logger.info("generateOTPandSendMail for mail id {} ", email);
		try {
			String temp = "Your account login ID :"+reg.getCustomerId() +"\nYour password :"+reg.getPassword()+
					"\nYour mortgage account number :"+reg.getMortgageAcc()+"\nYour transactional account number : "+reg.getTransactionAcc();
			
			String body = "Congratulations!!!! Your mortgage has been granted.,\n\nThis is the data you need and the same has been communicated over email. \n\n"+temp ;
			String subject = "Mortgage Granted";
			mailWithOTPService.sendEmail(email, subject, body);
		} catch (Exception e) {
			logger.info("Error in generating Mail ");
		}
	}

}
