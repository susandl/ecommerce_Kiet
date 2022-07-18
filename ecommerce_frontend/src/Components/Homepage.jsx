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
        });
        CustomerProduct.getProducts().then((response) => {
            this.setState({ categories : response.data})
        });

    }



    render() {
        return (
            <div>

                <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                    <div><h2 className='navbar-brand'>Ecommerce Index</h2></div>
                </nav>

                <div class="dropdown">
                    <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false">
                        Category
                    </button>
                    <ul className="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                       { this.state.categories.map(
                            category => 
                        <li><a className='dropdown-item'>{category.name}</a></li>
                    )}
                    </ul>
                </div>
            </div>

        );
    }
}

export default withRouter(Homepage);