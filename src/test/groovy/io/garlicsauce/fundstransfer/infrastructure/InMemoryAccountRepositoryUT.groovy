package io.garlicsauce.fundstransfer.infrastructure

import io.garlicsauce.fundstransfer.domain.Account
import spock.lang.Specification
import spock.lang.Subject

class InMemoryAccountRepositoryUT extends Specification {

    @Subject
    def repository = new InMemoryAccountRepository()

    def "should return empty optional when looking for non-existing account"() {
        expect:
            repository.findByOwnerId(UUID.randomUUID()) == Optional.empty()
    }

    def "should return optional of account"() {
        given:
            def accountId = UUID.randomUUID()
            def accountStub = Stub(Account)
            repository.repository.put(accountId, accountStub)

        expect:
            repository.findByOwnerId(accountId) == Optional.of(accountStub)
    }

    def "should save account"() {
        given:
            def accountMock = Mock(Account) { getOwnerId() >> UUID.randomUUID() }

        when:
            repository.save(accountMock)

        then:
            repository.findByOwnerId(accountMock.ownerId) == Optional.of(accountMock)
    }
}
