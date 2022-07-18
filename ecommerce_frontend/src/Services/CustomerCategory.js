import axios from "axios"
import authHeader from "./AuthHeader";
const CATEGORY_API_BASE_URL = "http://localhost:8080/customer/category";

class CustomerCategory{

    getCategories(){
        return axios.get(CATEGORY_API_BASE_URL, { headers: authHeader() });
    }
    
    getCategoryById(categoryId){
        return axios.get(CATEGORY_API_BASE_URL + '/' + categoryId, { headers: authHeader() });
    }
   

}
export default new CustomerCategory()