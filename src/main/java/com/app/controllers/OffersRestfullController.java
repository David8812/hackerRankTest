package com.app.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Offers;
import com.app.services.OffersService;

@RestController
@Path("/api/offers")
public class OffersRestfullController {

	@Autowired
	public OffersService offersService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		int statusCode = 200;
		Response response = null;
		List<Offers> offers = offersService.findAll();
		response = Response.status(statusCode).entity(offers).build();
		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Offers offers) {
		offers.setId(null);
		Offers savedOffer = offersService.save(offers);
		return Response.status(201).entity(savedOffer).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateOffer(@PathParam("id") final Long id, Offers o) {
		try {
			Offers offers = offersService.findById(id);
			offers.setStatus(o.isStatus());

			Offers savedOffer = offersService.save(offers);
			return Response.status(201).entity(savedOffer).build();
		} catch (NoSuchElementException e) {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/findOfferByPrice")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findOfferByPrice(@QueryParam("price") final Double price) {
		List<Offers> offers = offersService.findByPrice(price);

		return Response.status(201).entity(offers).build();
	}

	@GET
	@Path("/findOfferByStatus")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findOfferByStatus(@QueryParam("status") final boolean status) {
		List<Offers> offers = offersService.findByStatus(status);

		return Response.status(201).entity(offers).build();
	}
}
