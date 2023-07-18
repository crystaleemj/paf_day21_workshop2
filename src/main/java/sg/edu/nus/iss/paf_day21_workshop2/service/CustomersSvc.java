package sg.edu.nus.iss.paf_day21_workshop2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.paf_day21_workshop2.model.Customer;
import sg.edu.nus.iss.paf_day21_workshop2.model.Order;
import sg.edu.nus.iss.paf_day21_workshop2.repository.CustomersRepo;

@Service
public class CustomersSvc {
    
    @Autowired
    CustomersRepo repo;
    
    public List<Customer> listAllCustomers(Integer limit, Integer offset){
        return repo.listAllCustomers(limit, offset);
    }

    public Customer listCustomerById(Integer id){
        return repo.listCustomerByID(id);
    }

    public Boolean checkCustomerId(Integer id){
        Customer customer = listCustomerById(id);
        if (customer.getId() == id) {
            return true;
        }
        return false;
    }

    public List<Order> listOrdersById(Integer id){
        return repo.listAllOrdersByID(id);
    }

    public JsonObject customerIdError(Integer id){
        JsonObjectBuilder object = Json.createObjectBuilder()
        .add("errormsg", "Order not found with ID: " + id);

        return object.build();
    }
}
