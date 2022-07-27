import axios from "axios";
import authHeader from "./AuthHeader";
const CATEGORY_API_BASE_URL = "http://localhost:8080/admin/category";

class CategoryService{

    getCategories(){
        return axios.get(CATEGORY_API_BASE_URL, { headers: authHeader() });
    }
    createCategory(category){
        return axios.post(CATEGORY_API_BASE_URL + '/create',category ,{ headers: authHeader() });
    }
    getCategoryById(categoryId){
        return axios.get(CATEGORY_API_BASE_URL + '/' + categoryId, { headers: authHeader() });
    }
    updateCategory(category, categoryId){
        return axios.put(CATEGORY_API_BASE_URL + '/update/'+ categoryId, category,{ headers: authHeader() });
    }
    deleteCategory(categoryId){
        return axios.delete(CATEGORY_API_BASE_URL + "/delete/"+ categoryId,{ headers: authHeader() });
    }

}
export default new CategoryService()