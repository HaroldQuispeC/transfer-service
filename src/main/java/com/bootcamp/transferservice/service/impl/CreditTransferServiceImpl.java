package com.bootcamp.transferservice.service.impl;

import com.bootcamp.transferservice.exceptions.ModelException;
import com.bootcamp.transferservice.model.Client;
import com.bootcamp.transferservice.model.Constant;
import com.bootcamp.transferservice.model.Credit;
import com.bootcamp.transferservice.model.CreditTransfer;
import com.bootcamp.transferservice.repository.CreditTransferRepository;
import com.bootcamp.transferservice.service.ClientService;
import com.bootcamp.transferservice.service.CreditTransferService;
import com.bootcamp.transferservice.service.CreditTransferTypeFunction;
import com.bootcamp.transferservice.service.IncomeProductService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditTransferServiceImpl implements CreditTransferService {

  private static final String INVALID_ID_MESSAGE = "Invalid ID.";
  private static final Logger logger = LoggerFactory.getLogger(CreditTransferServiceImpl.class);

  @Autowired
  private ClientService feignClientClient;

  @Autowired
  private IncomeProductService feignClientIncomeProduct;


  @Autowired
  CreditTransferRepository creditTransferRepository;


  CreditTransferTypeFunction operatorFunction = (x) -> {
    int operator = 0;
    if (Constant.CreditTransferType.CHARGE.type == x) {
      operator = -1;
    }

    if (Constant.CreditTransferType.REFUND.type == x) {
      operator = +1;
    }

    if (Constant.CreditTransferType.CASH_ADVANCE.type == x) {
      operator = -1;
    }

    if (Constant.CreditTransferType.PENALTY.type == x) {
      operator = -1;
    }

    return operator;
  };

  @Override
  public Mono<CreditTransfer> create(CreditTransfer transfer) {
    logger.info("Create credit transfer");

    if (transfer == null) {
      throw new ModelException("Credit object null or invalid");
    }

    if (transfer.getClientID().isEmpty()) {
      throw new ModelException("Client ID null or invalid");
    }

    List<Client> clients = feignClientClient.getClients();
    if (clients.isEmpty()) {
      throw new ModelException("No clients found");
    }

    final String _clientId = transfer.getClientID();
    if (_clientId.equals("")) {
      throw new ModelException("Client: " + INVALID_ID_MESSAGE);
    }

    boolean exists = clients.stream().anyMatch(x ->
            x.getIdClient().equals(_clientId));

    if (!exists) {
      throw new ModelException(String.format("No client found with %s ID", _clientId));
    }

    if (transfer.getCreditTransferType() == null) {
      throw new ModelException("Invalid type of credit transfer.");
    }

    Credit oldCredit = feignClientIncomeProduct.findById(transfer.getCreditID());
    if (oldCredit == null) {
      throw new ModelException(String
              .format("Invalid credit with %s ID.", transfer.getCreditID().toString()));
    }

    transfer.setCreditTransferDate(LocalDateTime.now());
    transfer.setActive(true);
    transfer.setStatus("ACTIVE");

    long factor = operatorFunction.getCreditTransferOperator(Math.toIntExact(transfer
            .getCreditTransferType().getCreditTransferTypeID()));
    double amount = transfer.getCreditTransferAmount();
    double previousBalance = oldCredit.getBalance();
    double newBalance = previousBalance + amount * (factor);

    // oldCredit - edited
    oldCredit.setDebt(Math.abs(oldCredit.getDebt() + amount * (factor)));
    oldCredit.setBalance(newBalance);

    Mono<Long> monoMaxId = creditTransferRepository.findAll()
            .map(x -> x.getCreditTransferID())
            .reduce((x1, x2) -> x1 < x2 ? x2 : x1)
            .switchIfEmpty(Mono.just(0L));
    Long maxId = Objects.requireNonNull(monoMaxId.block()) + 1;
    transfer.setCreditTransferID(maxId);
    transfer.setPreviousBalance(previousBalance);
    transfer.setNewBalance(newBalance);

    // transactional ?
    feignClientIncomeProduct.update(oldCredit);
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
