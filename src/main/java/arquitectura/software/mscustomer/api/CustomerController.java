package arquitectura.software.mscustomer.api;


import arquitectura.software.mscustomer.entity.Customer;
import arquitectura.software.mscustomer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/customer")
public class CustomerController {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerRepository customerRepository;
    //@Value("${server.port}")
    //private String serverPort;

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public Customer saveCustomer(@RequestBody Customer customer){
        //System.out.println("REGISTRAR CLIENTE desde el puerto"+serverPort);
        LOGGER.info("Iniciando proceso para registrar cleinte: con los siguientes datos, {} ",customer);
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

    @RequestMapping(path = "/customerId", method = RequestMethod.GET)
    public Boolean getValueCustomerId(@RequestParam Integer customerId){
        Optional<Customer> customerOptional =customerRepository.findById(customerId);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            return true;
        }else{
            return false;
        }
    }



}
