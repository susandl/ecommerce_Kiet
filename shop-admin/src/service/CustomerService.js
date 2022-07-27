import axios from "axios"
import authHeader from "./AuthHeader";
const CUSTOMER_API_BASE_URL = "http://localhost:8080/admin/customer";
class CustomerService{

    getCustomers(){
        return axios.get(CUSTOMER_API_BASE_URL,{ headers: authHeader() });
    }
    createCustomer(customer){
        return axios.post(CUSTOMER_API_BASE_URL+ '/create',customer,{ headers: authHeader() });
    }
    
    getCustomerById(customerId){
        return axios.get(CUSTOMER_API_BASE_URL + '/' + customerId,{ headers: authHeader() });
    }
    updateCustomer(customer, customerId){
        return axios.put(CUSTOMER_API_BASE_URL + '/update/'+ customerId, customer,{ headers: authHeader() });
    }
    deleteCustomer(customerId){
        return axios.delete(CUSTOMER_API_BASE_URL + "/delete/"+ customerId,{ headers: authHeader() });
    }

}
export default new CustomerService()