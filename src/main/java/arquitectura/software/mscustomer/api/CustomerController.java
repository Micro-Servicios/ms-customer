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

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public Customer saveCustomer(@RequestBody Customer customer){
        LOGGER.info("Iniciando proceso para registrar cleinte: con los siguientes datos, {} ",customer);
        return customerRepository.save(customer);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Customer> getAllCustomer(){
        List<Customer> customers =customerRepository.findAll();
        if (customers.size()>0){
            LOGGER.info("SE ENCOTRO LOS DATOS DE LOS USUARIOS");
        }
        else LOGGER.warn("NO SE ENCONTRO LOS DATOS DE LOS USUARIOS");
        return customers;
    }

    @RequestMapping( method = RequestMethod.GET)
    public Customer getCustomer(@RequestParam Integer customerId) throws Exception {
        Optional<Customer> customerOptional =customerRepository.findById(customerId);
        if(customerOptional.isPresent()){
            LOGGER.info("SE ENCONTRO AL USUARIO");
            Customer customer = customerOptional.get();
            return customer;
        }else{
            LOGGER.error("NO SE ENCONTRO AL USUARIO");
            throw new Exception("No se encuentra el usuario");
        }
    }

    @RequestMapping(path = "/customerId", method = RequestMethod.GET)
    public Boolean getValueCustomerId(@RequestParam Integer customerId){
        Optional<Customer> customerOptional =customerRepository.findById(customerId);
        if(customerOptional.isPresent()){
            LOGGER.info("SE ENCONTRO EL USUARIO");
            return true;
        }else{
            LOGGER.error("NO EXISTE EL USUARIO");
            return false;
        }
    }



}
