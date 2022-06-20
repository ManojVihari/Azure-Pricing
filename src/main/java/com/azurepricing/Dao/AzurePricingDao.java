package com.azurepricing.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azurepricing.model.AzurePricing;

@Repository
public interface AzurePricingDao extends JpaRepository<AzurePricing,Long> {

}
