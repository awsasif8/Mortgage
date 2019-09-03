package com.ing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ing.dto.StatementDto;
import com.ing.dto.StatementResponseDto;
import com.ing.entity.Transaction;
import com.ing.repository.StatementRepository;

@Service

public class StatementServiceImpl implements StatementService {
	@Autowired
	StatementRepository statementRepository;

	@Override
	public StatementResponseDto getStatements(String accountNumber) {
		Pageable pageable = PageRequest.of(0, 5);
		List<Transaction> transaction = statementRepository.findByAccountNumber(accountNumber, pageable);
		StatementResponseDto dto1 = new StatementResponseDto();
		List<StatementDto> dto2 = new ArrayList<>();
		if (transaction.isEmpty()) {
			dto1.setMessage("details not displayed successfully check your accountnumber");
			dto1.setStatus("FAILURE");
			dto1.setStatusCode(200);
			return dto1;
		}

		else

		{
			for (Transaction transactions : transaction) {
				StatementDto dto = new StatementDto();
				dto.setDescription(transactions.getDescription());
				dto.setTransactionDate(transactions.getTransactionDate());
				dto.setTransactionId(transactions.getTransactionId());
				dto.setTransactionAmount(transactions.getTransactionAmount());
				dto.setTransactionType(transactions.getTransactionType());
				dto2.add(dto);

			}
			dto1.setData(dto2);
			dto1.setMessage("details displayed successfully");
			dto1.setStatus("SUCCESS");
			dto1.setStatusCode(200);
			return dto1;

		}

	}
}
