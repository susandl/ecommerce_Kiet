import React from "react";
import FooterComponent from "./Components/FooterComponent";
import HeaderComponent from "./Components/HeaderComponent";
import ListCustomerComponent from "./Components/ListCustomerComponent";
import './App.css';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import CreateCustomerComponent from "./Components/CreateCustomerComponent";
import UpdateCustomerComponent from "./Components/UpdateCustomerComponent";
import ListCategoryComponent from "./Components/ListCategoryComponent";
import CreateCategoryComponent from "./Components/CreateCategoryComponent";
import UpdateCategoryComponent from "./Components/UpdateCategoryComponent";
import ListProductComponent from "./Components/ListProductComponent";
import CreateProductComponent from "./Components/CreateProductComponent";
import UpdateProductComponent from "./Components/UpdateProductComponent";
import { history } from "./helper/history";
import Login from "./Components/Login";
import Homepage from "./Components/Homepage";
function App() {
  //check jwt token

  return (
    <div>
      <Router history={history}>
              <div className="container">
                <Routes>
                  <Route path="/login" element = {<Login/>}></Route>
                  <Route path="/admin" element = {<HeaderComponent/>}></Route>
                  <Route path="customers" element = {<ListCustomerComponent/>}></Route>
                  <Route path="add-customer" element = {<CreateCustomerComponent/>}></Route>
                  <Route path="update-customer/:id" element = {<UpdateCustomerComponent/>}></Route>
                  <Route path="categories" element = {<ListCategoryComponent/>}></Route>
                  <Route path="add-category" element = {<CreateCategoryComponent/>}></Route>
                  <Route path="update-category/:id" element = {<UpdateCategoryComponent/>}></Route>
                  <Route path="products" element = {<ListProductComponent/>}></Route>
                  <Route path="add-product" element = {<CreateProductComponent/>}></Route>
                  <Route path="update-product/:id" element = {<UpdateProductComponent/>}></Route>
                  <Route path="/homepage" element = {<Homepage/>}></Route>
                </Routes>
              </div>
            <FooterComponent/>
      </Router>
    </div>
  );
}
export default App;
