import {
  CalendarToday,
  LocationSearching,
  MailOutline,
  PermIdentity,
  PhoneAndroid,
  Publish,SecurityRounded
} from "@material-ui/icons";
import { Link, useParams } from "react-router-dom";
import "./user.css";
import { useState,useEffect } from "react";
import CustomerService from "../../service/CustomerService";
import { useHistory } from "react-router-dom";


export default function User(props) {
  const [user,setUser] = useState([]);
  let {userId} = useParams();
  let history = useHistory();
  console.log(userId);

  const handleEdit = (e)=>{
    e.preventDefault();
    let customer = {name: user.name,pass:user.pass}
    console.log(customer);
    CustomerService.updateCustomer(customer,userId).then(() =>{
      history.push("../users")
    })
  }

const changeCustomerNameHandler = (event) => {
    setUser((prev) => ({...prev,name:event.target.value}));
}
const changePasswordHandler = (event) => {
  setUser((prev) => ({...prev,pass:event.target.value}));
}
const cancle = () => {
  history.push("../users")
}


  useEffect(() => {
    const getUser = async () => {
        try {
            await CustomerService.getCustomerById(userId).then((res) =>{
              setUser(res.data);
              console.log(res.data)
            })
        } catch (err) {
            console.log(err);
        }
    }
    getUser();
}, [])

  return (
    <div className="user">
      <div className="userTitleContainer">
        <h1 className="userTitle">Edit User</h1>
        <Link to="/newUser">
          <button className="userAddButton">Create</button>
        </Link>
      </div>
      <div className="userContainer">
        <div className="userShow">
          <div className="userShowTop">
            <img
              src="https://images.pexels.com/photos/1152994/pexels-photo-1152994.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500"
              alt=""
              className="userShowImg"
            />
            <div className="userShowTopTitle">
              <span className="userShowUsername">{user.name}</span>
      
            </div>
          </div>
          <div className="userShowBottom">
            <span className="userShowTitle">Account Details</span>
            <div className="userShowInfo">
              <PermIdentity className="userShowIcon" />
              <span className="userShowInfoTitle">{user.name}</span>
            </div>
            <div className="userShowInfo">
              <SecurityRounded className="userShowIcon" />
              <span className="userShowInfoTitle">{user.pass}</span>
            </div>
            
          </div>
        </div>
        <div className="userUpdate">
          <span className="userUpdateTitle">Edit</span>
          <form className="userUpdateForm">
            <div className="userUpdateLeft">
              <div className="userUpdateItem">
                <label>Username</label>
                <input
                  type="text"
                  placeholder={user.name}
                  className="userUpdateInput" onChange={changeCustomerNameHandler}
                />
              </div>
              <div className="userUpdateItem">
                <label>Password</label>
                <input
                  type="text"
                  placeholder={user.pass}
                  className="userUpdateInput" onChange={changePasswordHandler}
                />
              </div>

            </div>
            <div className="userUpdateRight">
              
              <button className="userUpdateButton" onClick={handleEdit}>Update</button>
              <button className="userCancleButton" onClick={cancle} >Cancel</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
