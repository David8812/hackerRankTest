package com.app.dataRepositories;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Offers;

@Repository
public interface OffersRepository extends JpaRepository<Offers, Long> {

	public List<Offers> findByStatus(boolean status);

	public List<Offers> findByPrice(double price);

	public List<Offers> findByOfferTime(long offerTime);

	public List<Offers> findByBeginningTime(Date beginningTime);

	public List<Offers> findByDescription(String description);
}
