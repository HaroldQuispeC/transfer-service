package com.bootcamp.transferservice.service;

import com.bootcamp.transferservice.model.CreditTransfer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface CreditTransferService {

  Mono<CreditTransfer> create(CreditTransfer transfer);

  Mono<CreditTransfer> findById(String id);

  Flux<CreditTransfer> findAll();

  Mono<CreditTransfer> update(CreditTransfer transfer);

  Mono<Void> remove(String id);

  Mono<Void> remove(CreditTransfer transfer);

  Mono<Void> inactive(String id);

}

