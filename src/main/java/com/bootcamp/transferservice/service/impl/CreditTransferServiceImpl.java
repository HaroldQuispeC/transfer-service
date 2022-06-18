package com.bootcamp.transferservice.service.impl;

import com.bootcamp.transferservice.model.CreditTransfer;
import com.bootcamp.transferservice.repository.CreditTransferRepository;
import com.bootcamp.transferservice.service.CreditTransferService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditTransferServiceImpl  implements CreditTransferService {

  private static final Logger logger = LoggerFactory.getLogger(CreditTransferServiceImpl.class);

  @Autowired
  CreditTransferRepository creditTransferRepository;

  @Override
  public Mono<CreditTransfer> create(CreditTransfer transfer) {
    logger.info("Create credit transfer");
    return creditTransferRepository.save(transfer);
  }

  @Override
  public Mono<CreditTransfer> findById(String id) {
    logger.info("Find credit transfer by id");
    return creditTransferRepository.findById(id);
  }

  @Override
  public Flux<CreditTransfer> findAll() {
    logger.info("Find all credit transfer entities");
    return creditTransferRepository.findAll();
  }

  @Override
  public Mono<CreditTransfer> update(CreditTransfer transfer) {
    logger.info("Save credit transfer");
    return creditTransferRepository.save(transfer);
  }

  @Override
  public Mono<Void> remove(String id) {
    logger.info("Remove entity by id");
    return creditTransferRepository.deleteById(id);
  }

  @Override
  public Mono<Void> remove(CreditTransfer transfer) {
    logger.info("Remove entity");
    return creditTransferRepository.delete(transfer);
  }

  @Override
  public Mono<Void> inactive(String id) {
    return creditTransferRepository.findById(id).flatMap(c -> {
      c.setActive(false);
      c.setStatus("inactive");
      return creditTransferRepository.save(c).then();
    });
  }
}
