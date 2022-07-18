import React, { Component } from 'react';
import ProductService from '../Services/ProductService';
import { withRouter } from '../withRouter';
class UpdateProductComponent extends Component {
    constructor(props){
        super(props)
        
        this.state = {
            id: this.props.match.params.id,
            name: "",
            details: "",
            price: 0,
            categoryName: "",
        }
        this.changeProductNameHandler = this.changeProductNameHandler.bind(this);
        this.changeDetailsHandler = this.changeDetailsHandler.bind(this);
        this.changePriceHandler = this.changePriceHandler.bind(this);
        this.changeCategoryNameHandler = this.changeCategoryNameHandler.bind(this);
        this.updateProduct = this.updateProduct.bind(this);
    }

    componentDidMount(){
        ProductService.getProductById(this.state.id).then((res) => {
            let product  = res.data;
            this.setState({
                name: product.name,
                details: product.details,
                price: product.price
            });
        });
    }
    updateProduct = (e) => {
        e.preventDefault();
        let product = {name: this.state.name, details: this.state.details,price:this.state.price,categoryName:this.state.categoryName};
        console.log("product => " + JSON.stringify(product));
        ProductService.updateProduct(product, this.state.id).then( res => {
            this.props.navigate('/products');
        });

    }
    changeProductNameHandler= (event) => {
        this.setState({name: event.target.value});
    }
    changeDetailsHandler= (event) => {
        this.setState({details: event.target.value});
    }
    changePriceHandler= (event) => {
        this.setState({price: event.target.value});
    }
    changeCategoryNameHandler= (event) => {
        this.setState({categoryName: event.target.value});
    }
    cancel(){
        this.props.navigate("/products");
    }
    
    render() {
        return (
            <div>
                <div className='container'>
                    <div className='row'>
                        <div className='card col-md-6 offset-md-3'>
                            <h3 className='text-center'>Update Product</h3>
                            <div className='card-body'>
                                <form>
                                    <div className='form-group'>
                                        <label> Product Name: </label>
                                        <input placeholder='Product Name' name='ProductName' className='form-control'
                                            value={this.state.name} onChange={this.changeProductNameHandler}/>
                                    </div>
                                    <div className='form-group'>
                                        <label> Details: </label>
                                        <input placeholder='Details' name='details' className='form-control'
                                            value={this.state.details} onChange={this.changeDetailsHandler}/>
                                    </div>     
                                    <div className='form-group'>
                                        <label> Price: </label>
                                        <input placeholder='Price' name='price' className='form-control'
                                            value={this.state.price} onChange={this.changePriceHandler}/>
                                    </div>                       
                                    <div className='form-group'>
                                        <label> Category Name: </label>
                                        <input placeholder='Category Name' name='Category Name' className='form-control'
                                            value={this.state.categoryName} onChange={this.changeCategoryNameHandler}/>
                                    </div>  
                                    <button className='btn btn-success' onClick={this.updateProduct}>Update</button>
                                    <button className='btn btn-danger' onClick={this.cancel.bind(this)} style={{marginLeft:  "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>    
                    </div>    
                </div>
            </div>
        );
    }
}


export default withRouter(UpdateProductComponent);