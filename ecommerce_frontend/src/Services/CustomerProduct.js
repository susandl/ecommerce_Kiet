import axios from "axios"
import authHeader from "./AuthHeader";
const CATEGORY_API_BASE_URL = "http://localhost:8080/customer/product";

class CustomerProduct{

    getProducts(){
        return axios.get(CATEGORY_API_BASE_URL, { headers: authHeader() });
    }
    
    getProductById(productId){
        return axios.get(CATEGORY_API_BASE_URL + '/' + productId, { headers: authHeader() });
    }
   

}
export default new CustomerProduct()