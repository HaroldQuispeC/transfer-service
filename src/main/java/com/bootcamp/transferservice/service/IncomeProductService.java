package com.bootcamp.transferservice.service;

import com.bootcamp.transferservice.exceptions.FallbackException;
import com.bootcamp.transferservice.model.Credit;
import feign.Headers;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GATEWAY-SERVICE/api/incomes",
            fallback = IncomeProductService.IncomeProductServiceFallback.class)
public interface IncomeProductService {

  @GetMapping("{id}")
  @Headers("Content-Type: application/json")
  @CircuitBreaker(name = "income-product-service-cb")
  Credit findById(@PathVariable("id") String id);

  @GetMapping
  @Headers("Content-Type: application/json")
  @CircuitBreaker(name = "income-product-service-cb")
  List<Credit> findAll();

  @GetMapping("/search")
  @Headers("Content-Type: application/json")
  @CircuitBreaker(name = "income-product-service-cb")
  List<Credit> fetchCreditsByClientName(@RequestParam(value = "name") String name);

  @GetMapping("/business-client/{id}")
  @Headers("Content-Type: application/json")
  @CircuitBreaker(name = "income-product-service-cb")
  List<Credit> findByBusinessClientId(@PathVariable("id") String id);

  @GetMapping("/person-client/{id}")
  @Headers("Content-Type: application/json")
  @CircuitBreaker(name = "income-product-service-cb")
  Credit findByPersonClientId(@PathVariable("id") String id);

  @PutMapping
  @Headers("Content-Type: application/json")
  @CircuitBreaker(name = "income-product-service-cb")
  Credit update(@RequestBody Credit credit);

  @Component
  public class IncomeProductServiceFallback implements IncomeProductService {

    private final String defaultMessage = "IncomeProductService is unavailable at this moment";

    // ------------------------------ INCOME PRODUCT - CREDITS ------------------------------

    @Override
    public Credit findById(String id) {
      throw new FallbackException(String.format("findById: %s", defaultMessage));
    }

    @Override
    public List<Credit> findAll() {
      throw new FallbackException(String.format("findAll: %s", defaultMessage));
    }

    @Override
    public List<Credit> fetchCreditsByClientName(String name) {
      throw new FallbackException(String.format("fetchCreditsByClientName: %s", defaultMessage));
    }

    @Override
    public List<Credit> findByBusinessClientId(String id) {
      throw new FallbackException(String.format("findByBusinessClientId: %s", defaultMessage));
    }

    @Override
    public Credit findByPersonClientId(String id) {
      throw new FallbackException(String.format("findByPersonClientId: %s", defaultMessage));
    }

    @Override
    public Credit update(Credit credit) {
      throw new FallbackException(String.format("update - Credit: %s", defaultMessage));
    }
  }
}
