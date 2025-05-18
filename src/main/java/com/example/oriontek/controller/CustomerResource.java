package com.example.oriontek.controller;


import com.example.oriontek.domain.DataCreateCustomer;
import com.example.oriontek.domain.DataResponseCustomer;
import com.example.oriontek.model.Customer;
import com.example.oriontek.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;
import java.util.List;


@Path("/customers")
@Component
public class CustomerResource {

    private CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GET
    @Produces("application/json")
    public Response getAllCustomers() {
        List<DataResponseCustomer> responseCustomers = customerService.getAllCustomers();
        return responseCustomers.isEmpty() ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.ok(responseCustomers).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") Long id) {
        if (customerService.getCustomerById(id).isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        DataResponseCustomer responseCustomer = customerService.getCustomerById(id).map(customer ->
                new DataResponseCustomer(customer.getName(), customer.getEmail(), customer.getPhone(), customer.getCreatedAt().toString())).get();

        return Response.status(Response.Status.OK).entity(responseCustomer).build();
    }


    @PUT()
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updateCustomerById(DataCreateCustomer dataCreateCustomer, @PathParam("id") Long id) {

        try {
            DataCreateCustomer customer = customerService.updateCustomerById(dataCreateCustomer, id);
            return Response.ok(customer).build();
        }catch (EntityNotFoundException e){
            return Response
                    .status(Response.Status.NOT_FOUND).entity(STR."Customer with id: \{id} not found")
                    .build();        }
    }


    @POST()
    @Path("/create")
    @Consumes("application/json")
    public Response createCustomer(DataCreateCustomer dataCreateCustomer) {
        Customer customer = new Customer(dataCreateCustomer);
        customerService.saveCustomer(customer);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/deleteAll")
    public Response deleteAllCustomers() {
        customerService.deleteAllCustomers();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomerById(@PathParam("id") Long id) {
        customerService.deleteCustomerById(id);
        return Response.status(Response.Status.OK).build();
    }
}
