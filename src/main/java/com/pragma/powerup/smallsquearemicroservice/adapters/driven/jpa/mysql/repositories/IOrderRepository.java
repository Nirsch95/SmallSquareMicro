package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByIdClient(Long clientId);
}
