package sg.edu.nus.iss.paf_day21_workshop2.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_day21_workshop2.model.Customer;
import sg.edu.nus.iss.paf_day21_workshop2.model.Order;

@Repository
public class CustomersRepo {
    
    @Autowired
    JdbcTemplate template;

    private String listAllCustomers = "select * from customers limit ? offset ?";
    private String listCustomerByID = "select * from customers where id=?";
    private String listAllOrdersByID = "select * from orders where customer_id=?";


    public List<Customer> listAllCustomers(Integer limit, Integer offset){
        
        List<Customer> customerList = new ArrayList<>();
        customerList = template.query(listAllCustomers, BeanPropertyRowMapper.newInstance(Customer.class), limit, offset);

        return customerList;
    }

    public Customer listCustomerByID(Integer id){
        Customer customer = new Customer();
        customer = template.queryForObject(listCustomerByID, BeanPropertyRowMapper.newInstance(Customer.class), id);

        return customer;
    }

    public List<Order> listAllOrdersByID(Integer id){
        List<Order> orderList = new ArrayList<>();
        orderList = template.query(listAllOrdersByID, BeanPropertyRowMapper.newInstance(Order.class), id);

        return orderList;
    }


}
