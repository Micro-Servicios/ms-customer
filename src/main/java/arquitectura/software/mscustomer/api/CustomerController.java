package arquitectura.software.mscustomer.api;


import arquitectura.software.mscustomer.entity.Customer;
import arquitectura.software.mscustomer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public Customer saveCustomer(@RequestBody Customer customer){
        System.out.println("REGISTRAR CLIENTE desde el puerto"+serverPort);
        return customerRepository.save(customer);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    @RequestMapping( method = RequestMethod.GET)
    public Customer getCustomer(@RequestParam Integer customerId) throws Exception {
        Optional<Customer> customerOptional =customerRepository.findById(customerId);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            return customer;
        }else{
            throw new Exception("No se encuentra el usuario");
        }
    }



}
