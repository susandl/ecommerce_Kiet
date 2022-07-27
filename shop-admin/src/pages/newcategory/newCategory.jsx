import "../newProduct/newProduct.css";
import { useState } from "react";
import CategoryService from "../../service/CategoryService";
import { useHistory } from "react-router-dom";

export default function NewCategory() {
  const [category,setCategory] = useState({
    name:"",
    details:"",
  });
  let history = useHistory();

  const handleCreate = (e) => {
    e.preventDefault();
    
    console.log(category);
    CategoryService.createCategory(category).then(() =>{
      history.push("../categories")
    })
  }
  const changeName = (event) => {
    setCategory((prev) => ({...prev,name:event.target.value}));
}
const changeDetails = (event) => {
  setCategory((prev) => ({...prev,details:event.target.value}));
}

const cancle = () => {
  history.push("../categories")
}
  
  
  return (
    <div className="newProduct">
      <h1 className="addProductTitle">New Category</h1>
      <form className="addProductForm">
        
        <div className="addProductItem">
          <label>Name</label>
          <input type="text" placeholder="Name"  onChange={changeName}/>
        </div>
        <div className="addProductItem">
          <label>Details</label>
          <input type="text" placeholder="Details"  onChange={changeDetails}/>
        </div>
        
        <button className="addProductButton" onClick={(e)=> handleCreate(e)}>Create</button>
        <button className="cancleProductButton" onClick={cancle}>Cancle</button>
      </form>
    </div>
  );
}
