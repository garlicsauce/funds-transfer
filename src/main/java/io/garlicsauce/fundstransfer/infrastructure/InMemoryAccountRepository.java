package io.garlicsauce.fundstransfer.infrastructure;

import io.garlicsauce.fundstransfer.domain.Account;
import io.garlicsauce.fundstransfer.domain.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryAccountRepository implements AccountRepository {

    private final Map<UUID, Account> repository = new ConcurrentHashMap<>();

    @Override
    public synchronized Optional<Account> findByOwnerId(UUID ownerId) {
        return Optional.ofNullable(repository.get(ownerId));
    }

    @Override
    public synchronized void save(Account account) {
        repository.put(account.getOwnerId(), account);
    }
}
