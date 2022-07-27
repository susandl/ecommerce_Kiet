import axios from "axios"
import authHeader from "./AuthHeader";
const PRODUCT_API_BASE_URL = "http://localhost:8080/admin/product";
class ProductService{

    getProducts(){
        return axios.get(PRODUCT_API_BASE_URL,{ headers: authHeader() });
    }
    createProducts(product){
        return axios.post(PRODUCT_API_BASE_URL+'/create',product,{ headers: authHeader() });
    }
    
    getProductById(productId){
        return axios.get(PRODUCT_API_BASE_URL + '/' + productId,{ headers: authHeader() });
    }
    updateProduct(product, productId){
        return axios.put(PRODUCT_API_BASE_URL + '/update/'+ productId, product,{ headers: authHeader() });
    }
    deleteProduct(productId){
        return axios.delete(PRODUCT_API_BASE_URL + "/delete/"+ productId,{ headers: authHeader() });
    }

}
export default new ProductService()