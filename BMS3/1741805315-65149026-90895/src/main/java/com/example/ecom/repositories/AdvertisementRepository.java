package com.example.ecom.repositories;

import com.example.ecom.models.Advertisement;
import com.example.ecom.models.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {

    Iterable<Advertisement> findByPreference(Preference preference);
}
