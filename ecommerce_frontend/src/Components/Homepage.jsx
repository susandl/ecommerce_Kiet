import React, { Component } from 'react';
import { withRouter } from '../withRouter';
import { Link } from 'react-router-dom';
import CustomerCategory from '../Services/CustomerCategory';
import CustomerProduct from '../Services/CustomerProduct';
class Homepage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            categories: [],
            products: []
        }

    }
    componentDidMount(){
        CustomerCategory.getCategories().then((response) => {
            this.setState({ categories : response.data})
            console.log(this.state.categories)
        });
        CustomerProduct.getProducts().then((response) => {
            this.setState({ categories : response.data})
            console.log(this.state.products)
        });

    }



    render() {
        return (
            <div>

                <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                    <div><h2 className='navbar-brand'>Ecommerce Index</h2></div>
                </nav>

                <div className="dropdown">
                    <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false">
                        Category
                    </button>
                    <ul className="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                       
                    </ul>
                </div>
            </div>

        );
    }
}

export default withRouter(Homepage);