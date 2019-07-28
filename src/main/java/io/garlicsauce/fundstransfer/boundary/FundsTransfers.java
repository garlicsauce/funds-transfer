package io.garlicsauce.fundstransfer.boundary;

import io.garlicsauce.fundstransfer.api.FundsTransferRequest;
import io.garlicsauce.fundstransfer.domain.Account;
import io.garlicsauce.fundstransfer.domain.AccountNotFoundException;
import io.garlicsauce.fundstransfer.domain.AccountRepository;
import io.garlicsauce.fundstransfer.domain.MoneyExchanger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FundsTransfers {

    private final AccountRepository accountRepository;
    private final MoneyExchanger moneyExchanger;

    // should be @Transactional - but my inMemory implementation doesn't handle transactions
    public void transferFunds(FundsTransferRequest request) {
        Account senderAccount = accountRepository.findByOwnerId(request.getSenderId())
                .orElseThrow(AccountNotFoundException::new);
        Account recipientAccount = accountRepository.findByOwnerId(request.getSenderId())
                .orElseThrow(AccountNotFoundException::new);

        accountRepository.save(senderAccount.subtractMoney(request.getBalance(), moneyExchanger));
        accountRepository.save(recipientAccount.addMoney(request.getBalance(), moneyExchanger));

        log.info("Transferred {} {} from {} to {}", request.getBalance().getAmount(), request.getBalance().getCurrency(),
                request.getSenderId(), request.getRecipientId());
    }
}
