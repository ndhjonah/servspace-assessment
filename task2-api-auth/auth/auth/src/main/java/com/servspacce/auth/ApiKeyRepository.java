package com.servspacce.auth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    ApiKey findByKeyValue(String keyValue);
}