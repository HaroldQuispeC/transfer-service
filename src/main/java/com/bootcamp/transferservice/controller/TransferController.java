package com.bootcamp.transferservice.controller;

import com.bootcamp.transferservice.model.CreditTransfer;
import com.bootcamp.transferservice.service.CreditTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {
  /*
  @Autowired
  com.bootcamp.transferservice.service.CreditCardService creditCardFeignClient;
  @Autowired
  com.bootcamp.transferservice.service.IncomeProductService incomeProductServiceFeignClient;
   */
  @Autowired
  private CreditTransferService  creditTransferService;

  @GetMapping("{id}")
  public Mono<CreditTransfer> findById(@PathVariable("id") String id) {
    return this.creditTransferService.findById(id);
  }

  /**
   * findAll method.
   * @return Flux CreditTransfer
   */
  @GetMapping
  public Flux<CreditTransfer> findAll() {
    return this.creditTransferService.findAll();
  }

  @PostMapping
  public Mono<CreditTransfer> create(@RequestBody CreditTransfer transfer) {
    return creditTransferService.create(transfer);
  }

  @PutMapping
  public Mono<CreditTransfer> update(@RequestBody CreditTransfer card) {
    return creditTransferService.update(card);
  }

  @DeleteMapping("{id}")
  public Mono<Void> deleteById(@PathVariable("id") String id) {
    return creditTransferService.remove(id);
  }

  @DeleteMapping
  public Mono<Void> delete(@RequestBody CreditTransfer credit) {
    return creditTransferService.remove(credit);
  }

  @PostMapping("/{id}/inactive")
  public Mono<Void> inactive(@PathVariable("id") String id) {
    return creditTransferService.inactive(id);
  }
  
}
