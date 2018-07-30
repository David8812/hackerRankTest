package com.app.services.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dataRepositories.OffersRepository;
import com.app.model.Offers;
import com.app.model.StatusDescription;
import com.app.services.OffersService;

@Service
public class OffersServiceImpl implements OffersService {

	@Autowired
	OffersRepository offersRepository;

	@Override
	public Offers findById(Long id) {
		Offers offers = offersRepository.findById(id).get();
		StatusDescription statusDescription = getOfferStatusDescription(offers);
		offers.setStatusDescription(statusDescription);

		return offers;
	}

	@Override
	public List<Offers> findAll() {
		List<Offers> list = offersRepository.findAll();
		updateStatusDescription(list);

		return list;
	}

	@Override
	public List<Offers> findByStatus(boolean status) {
		List<Offers> list = offersRepository.findByStatus(status);
		updateStatusDescription(list);

		return list;
	}

	@Override
	public List<Offers> findByPrice(double price) {
		List<Offers> list = offersRepository.findByPrice(price);
		updateStatusDescription(list);

		return list;
	}

	@Override
	public Offers save(Offers offers) {
		StatusDescription statusDescription = getOfferStatusDescription(offers);
		offers.setStatusDescription(statusDescription);
		Offers o = offersRepository.save(offers);

		return o;
	}

	private void updateStatusDescription(List<Offers> list) {
		Iterator<Offers> it = list.iterator();
		while (it.hasNext()) {
			Offers o = it.next();
			StatusDescription statusDescription = getOfferStatusDescription(o);
			o.setStatusDescription(statusDescription);
		}
	}

	/**
	 * find the status description of an offer based on the beginning date and the
	 * time it last.
	 * 
	 * @param o
	 * @return StatusDescription
	 */
	private StatusDescription getOfferStatusDescription(Offers o) {
		// this method should handle the time offset, depending on some rule, but this
		// was not specified in problem description, so for simplicity lets obviate this
		// part and focus only in business rules
		StatusDescription statusDescription;
		if (o.isStatus()) {
			// get the date in when the offer begins
			Date startOfferDate = o.getBeginningTime();

			// calculate the date in when the offer ends from the beginning date and the
			// time that the offer last
			Date endOfferDate = new Date(startOfferDate.getTime() + o.getOfferTime());

			// assume the server has the correct time and date. Get the current time
			Date currentDate = new Date();

			// find if the current date is equal to the begin or end dates and handle the
			// cases
			if (currentDate.equals(startOfferDate))
				statusDescription = StatusDescription.ACTIVE;
			else if (currentDate.after(startOfferDate) && currentDate.before(endOfferDate))
				statusDescription = StatusDescription.ACTIVE;
			else if (currentDate.before(startOfferDate))
				statusDescription = StatusDescription.UNACTIVE;
			else {
				// USE CASE. Cancel the order if this expired or is equals to expiration date,
				// there are a lot of ways to handle this use cases, like create a Data base
				// trigger, or create a Thread that monitor this changes, but for simplicity and
				// like every request will come across this method this approach will be enough.
				statusDescription = StatusDescription.CANCELLED;
				o.setStatus(false);
			}
		} else
			statusDescription = StatusDescription.CANCELLED;
		return statusDescription;
	}
}
