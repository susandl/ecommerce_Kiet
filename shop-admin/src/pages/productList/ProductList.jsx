import "./productList.css";
import { DataGrid } from "@material-ui/data-grid";
import { DeleteOutline } from "@material-ui/icons";
import { productRows } from "../../dummyData";
import { Link } from "react-router-dom";
import { useState,useEffect } from "react";

import ProductService from "../../service/ProductService";

export default function ProductList() {
  const [data, setData] = useState(productRows);
  const [products,setProducts] = useState([]);
  
  const getProductList =  () => {
    try {
         ProductService.getProducts().then((res) =>{
           setProducts(res.data);
          console.log(res.data)
        })
    } catch (err) {
        console.log(err);
    }
}
  
  const handleDelete = (id) => {
    setData(products.filter((product) => product.id !== id));
    ProductService.deleteProduct(id);
    getProductList();
  };

  useEffect(() => {
    
    getProductList();
}, []);

  const columns = [
    { field: "id", headerName: "ID", width: 90 },
    {
      field: "name",
      headerName: "Product name",
      width: 200,
      
    },
    { field: "details", headerName: "Details", width: 300 },
    
    {
      field: "price",
      headerName: "Price",
      width: 160,
    },
    {
      field: "imageUrl",
      headerName: "ImageUrl",
      width: 200
    },
    {
      field: "action",
      headerName: "Action",
      width: 150,
      renderCell: (params) => {
        return (
          <>
            <Link to={"/product/" + params.row.id}>
              <button className="productListEdit">Edit</button>
            </Link>
            <DeleteOutline
              className="productListDelete"
              onClick={() => handleDelete(params.row.id)}
            />
          </>
        );
      },
    },
  ];

  return (
    
    <div className="productList">
      <Link to="/newProduct">
            <button className="userAddButton">Create</button>
          </Link>
      <DataGrid
        rows={products}
        disableSelectionOnClick
        columns={columns}
        pageSize={8}
        checkboxSelection
      />
    </div>
    
  );
}
