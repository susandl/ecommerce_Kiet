import "./newProduct.css";
import { useState } from "react";
import ProductService from "../../service/ProductService";
import { useHistory } from "react-router-dom";

export default function NewProduct() {
  const [product,setProduct] = useState({
    name:"",
    details:"",
    price:0,
    categoryName:"",
    imageUrl:"image.png"
  });
  let history = useHistory();

  const handleCreate = (e) => {
    e.preventDefault();
    
    console.log(product);
    ProductService.createProducts(product).then(() =>{
      history.push("../products")
    })
  }
  const changeName = (event) => {
    setProduct((prev) => ({...prev,name:event.target.value}));
}
const changeDetails = (event) => {
  setProduct((prev) => ({...prev,details:event.target.value}));
}
const changePrice = (e) => {
  setProduct((prev) => ({...prev,price:e.target.value}));
}
const changeCategoryName = (e) => {
  setProduct((prev) => ({...prev,categoryName:e.target.value}));
}
const cancle = () => {
  history.push("../products")
}
  
  
  return (
    <div className="newProduct">
      <h1 className="addProductTitle">New Product</h1>
      <form className="addProductForm">
        
        <div className="addProductItem">
          <label>Name</label>
          <input type="text" placeholder="Name"  onChange={changeName}/>
        </div>
        <div className="addProductItem">
          <label>Details</label>
          <input type="text" placeholder="Details"  onChange={changeDetails}/>
        </div>
        <div className="addProductItem">
          <label>Price</label>
          <input type="number" placeholder="Price"  onChange={changePrice}/>
        </div>
        <div className="addProductItem">
          <label>Category Name</label>
          <input type="text" placeholder="Category Name" onChange={changeCategoryName}/>
        </div>
        <button className="addProductButton" onClick={(e)=> handleCreate(e)}>Create</button>
        <button className="cancleProductButton" onClick={cancle}>Cancle</button>
      </form>
    </div>
  );
}
