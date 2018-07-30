package com.app.services;


import java.util.List;

import com.app.model.Offers;

public interface OffersService {

	public Offers findById(Long id);
	
	public Offers save(Offers offers);

	public List<Offers> findAll();

	public List<Offers> findByStatus(boolean status);

	public List<Offers> findByPrice(double price);

}
