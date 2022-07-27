import "./categoryList.css";
import { DataGrid } from "@material-ui/data-grid";
import { DeleteOutline } from "@material-ui/icons";
import { productRows } from "../../dummyData";
import { Link } from "react-router-dom";
import { useState,useEffect } from "react";
import "../product/product.css";
import CategoryService from "../../service/CategoryService";

export default function CategoryList() {
  const [data, setData] = useState(productRows);
  const [categories,setCategories] = useState([]);
  
  const getCategoryList = () => {
    try {
         CategoryService.getCategories().then( (res) =>{
             setCategories(res.data);
          console.log(res.data)
        })
    } catch (err) {
        console.log(err);
    }
}
  const handleDelete = (id) => {
    setCategories(categories.filter((product) => product.id !== id));
    CategoryService.deleteCategory(id);
    getCategoryList();
  };

  useEffect(() => {
    
    getCategoryList();
}, []);

  const columns = [
    { field: "id", headerName: "ID", width: 90 },
    {
      field: "name",
      headerName: "Category name",
      width: 200,
      
    },
    { field: "details", headerName: "Details", width: 600 },
    
    {
      field: "action",
      headerName: "Action",
      width: 150,
      renderCell: (params) => {
        return (
          <>
            <Link to={"/category/" + params.row.id}>
              <button className="categoryListEdit">Edit</button>
            </Link>
            <DeleteOutline
              className="categoryListDelete"
              onClick={() => handleDelete(params.row.id)}
            />
          </>
        );
      },
    },
  ];

  return (
    
    <div className="categoryList">
      <Link to="/newcategory">
            <button className="userAddButton">Create</button>
          </Link>
      <DataGrid
        rows={categories}
        disableSelectionOnClick
        columns={columns}
        pageSize={8}
        checkboxSelection
      />
    </div>
    
  );
}
