import "./newUser.css";
import { useState } from "react";
import CustomerService from "../../service/CustomerService";
import { useHistory } from "react-router-dom";


export default function NewUser() {
  const [user,setUser] = useState({
    username:"",
    password:"",
    role:"ADMIN"
  });
  let history = useHistory();

  const handleCreate = (e) => {
    e.preventDefault();
    
    console.log(user);
    CustomerService.createCustomer(user).then(() =>{
      history.push("../users")
    })
  }
  const changeCustomerNameHandler = (event) => {
    setUser((prev) => ({...prev,username:event.target.value}));
}
const changePasswordHandler = (event) => {
  setUser((prev) => ({...prev,password:event.target.value}));
}
const changeRoleHandler = (e) => {
  setUser((prev) => ({...prev,role:e.target.value}));
}
const cancle = () => {
  history.push("../users")
}
  return (
    <div className="newUser">
      <h1 className="newUserTitle">New User</h1>
      <form className="newUserForm">
        <div className="newUserItem">
          <label>Username</label>
          <input type="text" placeholder="username" onChange={changeCustomerNameHandler}/>
        </div>
        <div className="newUserItem">
          <label>PassWord</label>
          <input type="text" placeholder="password" onChange={changePasswordHandler}/>
        </div>
        
        <div className="newUserItem">
          <label>Role</label>
          <select className="newUserSelect" name="active" id="active"   onChange={(e)=> changeRoleHandler(e)}>
            <option value="ADMIN" selected="selected">Admin</option>
            <option value="CUSTOMER">Customer</option>
          </select>
        </div>
      </form>
      <button className="newUserButton" onClick={handleCreate}>Create</button>
      <button className="newUserButton" onClick={cancle}>Cancle</button>
    </div>
  );
}
