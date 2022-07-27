import axios from "axios";
const AXIOS_API_URL = 'http://localhost:8080';
class LoginService {
  login(username, password) {
    const url = AXIOS_API_URL + "/auth/signin";

    console.log(url);
    return axios.post(url, { name: username, pass: password });
  }
}
export default  new LoginService()