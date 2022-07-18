import axios from "axios"
import authHeader from "./AuthHeader";
const CATEGORY_API_BASE_URL = "http://localhost:8080/admin/category";

class CategoryService{

    getCategories(){
        return axios.get(CATEGORY_API_BASE_URL, { headers: authHeader() });
    }
    createCategory(customer){
        return axios.post(CATEGORY_API_BASE_URL,customer + '/create' ,{ headers: authHeader() });
    }
    getCategoryById(categoryId){
        return axios.get(CATEGORY_API_BASE_URL + '/' + categoryId, { headers: authHeader() });
    }
    updateCategory(category, categoryId){
        return axios.put(CATEGORY_API_BASE_URL + '/update/'+ categoryId, category,{ headers: authHeader() });
    }
    deleteCustomer(categoryId){
        return axios.delete(CATEGORY_API_BASE_URL + "/delete/"+ categoryId,{ headers: authHeader() });
    }

}
export default new CategoryService()