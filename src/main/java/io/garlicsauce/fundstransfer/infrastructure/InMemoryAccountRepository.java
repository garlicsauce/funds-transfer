package io.garlicsauce.fundstransfer.infrastructure;

import io.garlicsauce.fundstransfer.domain.Account;
import io.garlicsauce.fundstransfer.domain.AccountRepository;
import io.garlicsauce.fundstransfer.shared.AmountAndCurrency;
import io.garlicsauce.fundstransfer.shared.Currency;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryAccountRepository implements AccountRepository {

    private final Map<UUID, Account> repository;

    public InMemoryAccountRepository() {
        this.repository = new ConcurrentHashMap<>();

        this.repository.put(UUID.fromString("e719da9b-dbf4-41bd-a802-cf8d5fb9e6e4"),
                new Account(UUID.fromString("e719da9b-dbf4-41bd-a802-cf8d5fb9e6e4"),
                        new AmountAndCurrency(new BigDecimal("1000"), Currency.EUR)));
        this.repository.put(UUID.fromString("d8d4f285-d214-4ce4-81cc-1faab82f1bb6"),
                new Account(UUID.fromString("d8d4f285-d214-4ce4-81cc-1faab82f1bb6"),
                        new AmountAndCurrency(new BigDecimal("200"), Currency.EUR)));
        this.repository.put(UUID.fromString("eab3d3f0-b1c9-4e42-8ce4-17f43b476e8e"),
                new Account(UUID.fromString("eab3d3f0-b1c9-4e42-8ce4-17f43b476e8e"),
                        new AmountAndCurrency(new BigDecimal("5000"), Currency.CHF)));
        this.repository.put(UUID.fromString("0c3b8117-55fb-4e15-8702-c10a691edfbf"),
                new Account(UUID.fromString("0c3b8117-55fb-4e15-8702-c10a691edfbf"),
                        new AmountAndCurrency(new BigDecimal("2000"), Currency.PLN)));
    }

    @Override
    public synchronized Optional<Account> findByOwnerId(UUID ownerId) {
        return Optional.ofNullable(repository.get(ownerId));
    }

    @Override
    public synchronized void save(Account account) {
        repository.put(account.getOwnerId(), account);
    }
}
