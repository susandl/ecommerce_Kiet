import { Link } from "react-router-dom";
import "./product.css";
import "../user/user.css"
import Chart from "../../components/chart/Chart"
import {productData} from "../../dummyData"
import {
    CalendarToday,
    LocationSearching,
    Details,
    PermIdentity,
    FormatListNumbered,
    Image,SecurityRounded,TimelapseSharp,Timelapse
  } from "@material-ui/icons";
import ProductService from "../../service/ProductService";
import { useState,useEffect } from "react";
import { useHistory, useParams } from "react-router-dom";
export default function Product() {
    const [product,setProduct] = useState([]);
    const [errorMessage,setErrorMessage] = useState(null);
    let {productId} = useParams();
    let history = useHistory();
    console.log(productId);
  
    const handleEdit = (e)=>{
      e.preventDefault();
      
      console.log(product);
      ProductService.updateProduct(product,productId).then(() =>{
        history.push("../products")
      }).catch( (error) => {
        if(error.response){
            setErrorMessage(error.response.data);
        }

      })
    }
  
  const changeProductName = (event) => {
      setProduct((prev) => ({...prev,name:event.target.value}));
  }
  const changeProductDetail = (event) => {
    setProduct((prev) => ({...prev,details:event.target.value}));
  }
  const changeCategoryName = (event) => {
    setProduct((prev) => ({...prev,categoryName:event.target.value}));
  }
  const changeProductPrice = (event) => {
    setProduct((prev) => ({...prev,price:event.target.value}));
  }
  const changeProductImageURL = (event) => {
    setProduct((prev) => ({...prev,imageUrl:event.target.value}));
  }
  const cancle = () => {
    history.push("../products")
  }
  
  useEffect(() => {
    const getProduct = async () => {
        try {
            await ProductService.getProductById(productId).then((res) =>{
              setProduct(res.data);
              console.log(res.data)
            })
        } catch (err) {
            console.log(err);
        }
    }
    getProduct();
}, [])
  
    return (
        <div className="user">
        <div className="userTitleContainer">
          <h1 className="userTitle">Edit Product</h1>
          <Link to="/newProduct">
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
                <span className="userShowUsername">{product.name}</span>
        
              </div>
            </div>
            <div className="userShowBottom">
              <span className="userShowTitle">Product Details</span>
              <div className="userShowInfo">
                <PermIdentity className="userShowIcon" />
                <span className="userShowInfoTitle">{product.name}</span>
              </div>
              <div className="userShowInfo">
                <Details className="userShowIcon" />
                <span className="userShowInfoTitle">{product.details}</span>
              </div>
              <div className="userShowInfo">
                <FormatListNumbered className="userShowIcon" />
                <span className="userShowInfoTitle">{product.price}</span>
              </div>
              <div className="userShowInfo">
                <Image className="userShowIcon" />
                <span className="userShowInfoTitle">{product.imageUrl}</span>
              </div>
              <div className="userShowInfo">
                <TimelapseSharp className="userShowIcon" />
                <span className="userShowInfoTitle">{product.createdDate}</span>
              </div>
              <div className="userShowInfo">
                <Timelapse className="userShowIcon" />
                <span className="userShowInfoTitle">{product.updatedDate}</span>
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
                    placeholder={product.name}
                    className="userUpdateInput" onChange={changeProductName}
                  />
                </div>
                <div className="userUpdateItem">
                  <label>Product Details</label>
                  <input
                    type="text"
                    placeholder={product.details}
                    className="userUpdateInput" onChange={changeProductDetail}
                  />
                </div>
                <div className="userUpdateItem">
                  <label>Product Price</label>
                  <input
                    type="text"
                    placeholder={product.price}
                    className="userUpdateInput" onChange={changeProductPrice}
                  />
                </div>
                <div className="userUpdateItem">
                  <label>Product Image URL</label>
                  <input
                    type="text"
                    placeholder={product.imageUrl}
                    className="userUpdateInput" onChange={changeProductImageURL}
                  />
                </div>
                <div className="userUpdateItem">
                  <label>Product's Category Name</label>
                  <input
                    type="text"
                    placeholder="Category Name"
                    className="userUpdateInput" onChange={changeCategoryName}
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
