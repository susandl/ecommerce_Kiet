import Sidebar from "./components/sidebar/Sidebar";
import Topbar from "./components/topbar/Topbar";
import "./App.css";
import Home from "./pages/home/Home";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import UserList from "./pages/userList/UserList";
import User from "./pages/user/User";
import NewUser from "./pages/newUser/NewUser";
import ProductList from "./pages/productList/ProductList";
import Product from "./pages/product/Product";
import NewProduct from "./pages/newProduct/NewProduct";
import LoginPage from "./pages/login/login";
import Category from "./pages/category/Category";
import CategoryList from "./pages/categoryList/categoryList";
import NewCategory from "./pages/newcategory/newCategory";
function App() {
  return (
    <Router>
      

      
        <Switch>
        <Route path='/login'>
            <LoginPage />
            </Route>
          <Route exact path="/">
          <Topbar />
      <div className="container">
        <Sidebar />
            <Home /></div>
          </Route>
          <Route path="/users">
          <Topbar />
      <div className="container">
        <Sidebar />
            <UserList /></div>
          </Route>
          <Route path="/user/:userId">
          <Topbar />
      <div className="container">
        <Sidebar />
            <User /></div>
          </Route>
          <Route path="/newUser">
          <Topbar />
      <div className="container">
        <Sidebar />
            <NewUser /></div>
          </Route>
          <Route path="/products">
          <Topbar />
      <div className="container">
        <Sidebar />
            <ProductList /></div>
          </Route>
          <Route path="/product/:productId">
          <Topbar />
      <div className="container">
        <Sidebar />
            <Product /></div>
          </Route>
          <Route path="/newproduct">
          <Topbar />
      <div className="container">
        <Sidebar />
            <NewProduct />
            </div>
          </Route>
          <Route path="/category/:categoryId">
          <Topbar />
      <div className="container">
        <Sidebar />
            <Category />
            </div>
          </Route>
          <Route path="/newcategory">
          <Topbar />
      <div className="container">
        <Sidebar />
            <NewCategory />
            </div>
          </Route>
          <Route path="/categories">
          <Topbar />
      <div className="container">
        <Sidebar />
            <CategoryList />
            </div>
          </Route>
        </Switch>
      
      
    </Router>
  );
}

export default App;
