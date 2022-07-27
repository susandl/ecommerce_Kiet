import "./userList.css";
import { DataGrid } from "@material-ui/data-grid";
import { DeleteOutline } from "@material-ui/icons";
import { userRows } from "../../dummyData";
import { Link } from "react-router-dom";
import { useState,useEffect } from "react";
import CustomerService from "../../service/CustomerService";

export default function UserList() {
  const [data, setData] = useState(userRows);
  const [users,setUsers] = useState([]);
  
  const getUserList =  () => {
    try {
         CustomerService.getCustomers().then((res) =>{
          setUsers(res.data);
          console.log(res.data)
        })
    } catch (err) {
        console.log(err);
    }
}
  useEffect(() => {
    
    getUserList();
}, [])

  const handleDelete = (id) => {
    setData(users.filter((user) => user.id !== id));
    CustomerService.deleteCustomer(id);
    getUserList();
  };
  
  const columns = [
    { field: "id", headerName: "ID", width: 90 },
    {
      field: "name",
      headerName: "Username",
      width: 200,
      
    },
    
    {
      field: "pass",
      headerName: "Password",
      width: 200,
    },
    {
      field: "action",
      headerName: "Action",
      width: 150,
      renderCell: (params) => {
        return (
          <>
            <Link to={"/user/" + params.row.id}>
              <button className="userListEdit">Edit</button>
            </Link>
            <DeleteOutline
              className="userListDelete"
              onClick={() => {
                handleDelete(params.row.id);
                }
              }
            />
          </>
        );
      },
    },
  ];

  return (
    <div className="userList">
      <Link to="/newUser">
          <button className="userAddButton">Create</button>
        </Link>
      <DataGrid
        rows={users}
        disableSelectionOnClick
        columns={columns}
        pageSize={8}
        checkboxSelection
      />
    </div>
  );
}
