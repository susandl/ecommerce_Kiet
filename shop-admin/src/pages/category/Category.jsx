import CategoryService from "../../service/CategoryService";
import "../user/user.css"
import { useState,useEffect } from "react";
import { useHistory, useParams,Link } from "react-router-dom";
import {
    Details,
    PermIdentity,
   
  } from "@material-ui/icons";

export default function Category() {
    const [category,setCategory] = useState([]);
    const [errorMessage,setErrorMessage] = useState(null);
    let {categoryId} = useParams();
    let history = useHistory();
    console.log(categoryId);
  
    const handleEdit = (e)=>{
      e.preventDefault();
      
      console.log(category);
      CategoryService.updateCategory(category,categoryId).then(() =>{
        history.push("../categories")
      }).catch( (error) => {
        if(error.response){
            setErrorMessage(error.response.data);
        }

      })
    }
  
  const changeCategoryName = (event) => {
    setCategory((prev) => ({...prev,name:event.target.value}));
  }
  const changeCategoryDetail = (event) => {
    setCategory((prev) => ({...prev,details:event.target.value}));
  }
  
  const cancle = () => {
    history.push("../categories")
  }
  
  useEffect(() => {
    const getCategory = async () => {
        try {
            await CategoryService.getCategoryById(categoryId).then((res) =>{
              setCategory(res.data);
              console.log(res.data)
            })
        } catch (err) {
            console.log(err);
        }
    }
    getCategory();
}, [])
  
    return (
        <div className="user">
        <div className="userTitleContainer">
          <h1 className="userTitle">Edit Category</h1>
          <Link to="/newcategory">
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
                <span className="userShowUsername">{category.name}</span>
        
              </div>
            </div>
            <div className="userShowBottom">
              <span className="userShowTitle">Category Details</span>
              <div className="userShowInfo">
                <PermIdentity className="userShowIcon" />
                <span className="userShowInfoTitle">{category.name}</span>
              </div>
              <div className="userShowInfo">
                <Details className="userShowIcon" />
                <span className="userShowInfoTitle">{category.details}</span>
              </div>
              
            </div>
          </div>
          <div className="userUpdate">
            <span className="userUpdateTitle">Edit</span>
            <form className="userUpdateForm">
              <div className="userUpdateLeft">
                <div className="userUpdateItem">
                  <label>Product Name</label>
                  <input
                    type="text"
                    placeholder={category.name}
                    className="userUpdateInput" onChange={changeCategoryName}
                  />
                </div>
                <div className="userUpdateItem">
                  <label>Product Details</label>
                  <input
                    type="text"
                    placeholder={category.details}
                    className="userUpdateInput" onChange={changeCategoryDetail}
                  />
                </div>
              </div>
              <div className="userUpdateRight">
                <button className="userUpdateButton" onClick={handleEdit}>Update</button>
                <button className="userCancleButton" onClick={cancle} >Cancel</button>
              </div>
            </form>
            {/*  handle error */ }
          </div>
        </div>
      </div>
  );
}