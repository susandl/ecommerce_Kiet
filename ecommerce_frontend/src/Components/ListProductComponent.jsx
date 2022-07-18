import React, { Component } from 'react';
import ProductService from '../Services/ProductService';
import { withRouter } from '../withRouter';
class ListProductComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            products: []
        }
        this.addProduct = this.addProduct.bind(this);
        this.updateProduct= this.updateProduct.bind(this);
        this.deleteProduct = this.deleteProduct.bind(this);
    }
    componentDidMount(){
        ProductService.getProducts().then((response) => {
            this.setState({ products : response.data})
        });
    }
    addProduct(){
        this.props.navigate("/add-product")
    }
    updateProduct(id){
        this.props.navigate("/update-product/${id}");
    }
    deleteProduct(id){
        ProductService.deleteProduct(id).then(res => {
            this.setState({products: this.state.products.filter(product => product.id !== id)});
        });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Product List</h2>
                <div className='row'>
                    <button className='btn btn-primnary' onClick={this.addProduct}> Add Product</button>
                </div>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Product Details</th>
                                <th>Product Price</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.products.map(
                                    product =>
                                    <tr key={product.id}>
                                        <td>{product.name}</td>
                                        <td>{product.details}</td>
                                        <td>{product.price}</td>
                                        <td>
                                            <button onClick= {() => this.updateProduct(product.id)} className= "btn btn-info">Update</button>
                                            <button onClick= {() => this.deleteProduct(product.id)} className= "btn btn-danger">Delete</button>
                                        </td>
                                    </tr>

                                )
                            }

                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default withRouter(ListProductComponent);