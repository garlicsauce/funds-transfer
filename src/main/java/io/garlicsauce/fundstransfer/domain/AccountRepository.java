package io.garlicsauce.fundstransfer.domain;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    Optional<Account> findByOwnerId(UUID ownerId);

    void save(Account account);
}
