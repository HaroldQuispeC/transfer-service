package com.bootcamp.transferservice.repository;

import com.bootcamp.transferservice.model.CreditTransfer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditTransferRepository extends ReactiveMongoRepository<CreditTransfer, String> {
}