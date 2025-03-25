package com.example.qcommerce.repositories;

import com.example.qcommerce.models.PartnerTaskMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerTaskRepository extends JpaRepository<PartnerTaskMapping, Long> {
}
