package sg.edu.nus.iss.paf_day21_workshop2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.paf_day21_workshop2.model.Customer;
import sg.edu.nus.iss.paf_day21_workshop2.model.Order;
import sg.edu.nus.iss.paf_day21_workshop2.service.CustomersSvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/api")
public class CustomersRestController {

    @Autowired
    CustomersSvc svc;
    
    // content-type: url-form-encoded -> consumes = ""
    // accepts: application/json -> produces = ""
    //The default value for offset is 0 and limit is 5.
    @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> listAllCustomers(@RequestParam (defaultValue = "5", required = false) Integer limit, @RequestParam (defaultValue = "0", required = false) Integer offset) {
        List<Customer> list = svc.listAllCustomers(limit, offset);
        System.out.println(list);

        return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
    }
    
    @GetMapping(path = "/customer/{customer_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listCustomerById(@PathVariable Integer customer_id) {

        // if (!svc.checkCustomerId(customer_id)) {
        //     return new ResponseEntity<>(svc.customerIdError(customer_id).toString(), HttpStatus.NOT_FOUND);
        // }
        
        Customer customer = new Customer();
        customer = svc.listCustomerById(customer_id);

        // if (customer == null) {
        //     return new ResponseEntity<>(svc.customerIdError(customer_id).toString(), HttpStatus.NOT_FOUND);
        // }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @GetMapping(path = "/customer/{customer_id}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listOrdersById(@PathVariable Integer customer_id) {

        List<Order> list = svc.listOrdersById(customer_id);

        if (list.isEmpty()) {
            return new ResponseEntity<>(svc.customerIdError(customer_id).toString(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
    }
    
    
}
