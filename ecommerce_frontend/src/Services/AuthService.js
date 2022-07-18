import axios from "axios";
const API_URL = "http://localhost:8080/auth/";
class AuthService {
  login(username, password) {
    return axios
      .post(API_URL + "signin", {
        name:username,
        pass:password
      })
      .then(response => {
        console.log(response.data)
        if (response.data.token) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
      });
  }
  logout() {
    localStorage.removeItem("user");
  }
  register(name, pass) {
    return axios.post(API_URL + "signup", {
      name,
      pass,
    });
  }
  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
}
export default new AuthService();