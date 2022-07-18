import { NavLink } from 'react-router-dom'

const Sidebar = () => {
  return (
    <>
      <h2 className="my-3 text-white">Ecommerce management</h2>
      <div className="pt-3">
        <ul className="nav nav-pills flex-column">
          <li className="nav-item">
          <ul className="mt-1">
            <NavLink to="/admin/customers" className="nav-link">Customer</NavLink>
            <li className="nav-item">
                <NavLink to="/customers/add-customer" className="nav-link">Add</NavLink>
              </li>
              </ul>
          </li>
          <li className="nav-item">
            <NavLink to="/admin/categories" className="nav-link">Category</NavLink>
            <ul className="mt-1">
              <li className="nav-item">
                <NavLink to="/categories/add-category" className="nav-link">Add</NavLink>
              </li>
              </ul>
          </li>
          <li className="nav-item">
            <NavLink to="/admin/products" className="nav-link">Products</NavLink>
            <ul className="mt-1">
              <li className="nav-item">
                <NavLink to="/products/add-product" className="nav-link">Add</NavLink>
              </li>
            </ul>
          </li>
          <li className="nav-item">
            <NavLink to="/login" className="nav-link">Log out</NavLink>
          </li>
        </ul>
      </div>
    </>
  )
}

export default Sidebar