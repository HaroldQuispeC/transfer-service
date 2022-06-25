package com.bootcamp.transferservice.service;

import com.bootcamp.transferservice.exceptions.FallbackException;
import com.bootcamp.transferservice.model.CreditCard;
import feign.Headers;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GATEWAY-SERVICE/api/incomes/credit-cards",
            fallback = CreditCardService.CreditCardServiceFallback.class)
public interface CreditCardService {

  @GetMapping
  @Headers("Content-Type: application/json")
  @CircuitBreaker(name = "income-product-service-cb")
  List<CreditCard> findAll();

  @GetMapping("/business-client/{id}")
  @Headers("Content-Type: application/json")
  @CircuitBreaker(name = "income-product-service-cb")
  List<CreditCard> findByBusinessClientId(@PathVariable("id") String id);

  @GetMapping("/person-client/{id}")
  @Headers("Content-Type: application/json")
  @CircuitBreaker(name = "income-product-service-cb")
  List<CreditCard> findByPersonClientId(@PathVariable("id") String id);

  @GetMapping("/business-client/search")
  @Headers("Content-Type: application/json")
  @CircuitBreaker(name = "income-product-service-cb")
  List<CreditCard> findByBusinessClient(@RequestParam(value = "ruc") String ruc);

  @GetMapping("/person-client/search")
  @Headers("Content-Type: application/json")
  @CircuitBreaker(name = "income-product-service-cb")
  List<CreditCard> findByPersonClient(@RequestParam(value = "dni") String dni);

  @Component
  class CreditCardServiceFallback implements CreditCardService {

    private static final String DEFAULT_MESSAGE
            = "Income Product Service is unavailable at this moment.";

    // ------------------------------   CREDIT CARD ------------------------------
    @Override
    public List<CreditCard> findAll() {
      throw new FallbackException(String.format("findAll: %s", DEFAULT_MESSAGE));
    }

    @Override
    public List<CreditCard> findByBusinessClientId(String id) {
      throw new FallbackException(String.format("findByBusinessClientId: %s", DEFAULT_MESSAGE));
    }

    @Override
    public List<CreditCard> findByPersonClientId(String id) {
      throw new FallbackException(String.format("findByPersonClientId: %s", DEFAULT_MESSAGE));
    }

    @Override
    public List<CreditCard> findByBusinessClient(String ruc) {
      throw new FallbackException(String.format("findByBusinessClient: %s", DEFAULT_MESSAGE));
    }

    @Override
    public List<CreditCard> findByPersonClient(String dni) {
      throw new FallbackException(String.format("findByPersonClient: %s", DEFAULT_MESSAGE));
    }
  }
}
