package com.example.oriontek.controller;

import com.example.oriontek.domain.DataCreateAddress;
import com.example.oriontek.domain.DataResponseAddress;
import com.example.oriontek.model.Address;
import com.example.oriontek.service.AddressService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Path("/address")
@Component
public class AddressResource {

    private AddressService addressService;

    public AddressResource(AddressService addressService) {
        this.addressService = addressService;
    }

    @GET
    @Produces("application/json")
    @Path("/{customerId}")
    public Response getAllAddressByCustomerId(@PathParam("customerId") Long customerId) {
        List<DataResponseAddress> responseAddresses = addressService.getAllAddressByCustomerId(customerId);
        return responseAddresses.isEmpty() ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.ok(responseAddresses).build();
    }

    @GET
    @Produces("application/json")
    public Response getAllAddress() {
        List<DataResponseAddress> responseAddresses = addressService.getAllAddress();
        return responseAddresses.isEmpty() ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.ok(responseAddresses).build();
    }

    @POST()
    @Path("/create")
    @Consumes("application/json")
    public Response createAddress(DataCreateAddress dataCreateAddress) {

        try {
            Address address = new Address(dataCreateAddress);
            addressService.saveAddress(address, dataCreateAddress.idCustomer());

        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAddressById(@PathParam("id") Long id) {
        try {
            addressService.deleteAddressById(id);
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).build();
    }
}
