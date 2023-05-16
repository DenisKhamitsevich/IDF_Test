package com.task.repository;

import com.task.entity.Cryptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoRepository extends JpaRepository<Cryptocurrency, Long> {

}
