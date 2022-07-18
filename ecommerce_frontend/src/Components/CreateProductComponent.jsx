import React, { Component } from 'react';
import ProductService from '../Services/ProductService';
import { withRouter } from '../withRouter';
class CreateProductComponent extends Component {
    constructor(props){
        super(props)
        
        this.state = {
            name: "",
            details: "",
            price:0 ,
            categoryName: "",
            
        }
        this.changeProductNameHandler = this.changeProductNameHandler.bind(this);
        this.changeDetailsHandler = this.changeDetailsHandler.bind(this);
        this.changePriceHandler = this.changePriceHandler.bind(this);
        this.changeCategoryNameHandler = this.changeCategoryNameHandler.bind(this);
        this.saveProduct = this.saveProduct.bind(this);
    }
    saveProduct = (e) => {
        e.preventDefault();
        let product = {name: this.state.name, details: this.state.details, price:this.state.price,categoryName: this.state.categoryName};
        console.log("product => " + JSON.stringify(product));

        ProductService.createProducts(product).then(res =>{
            this.props.navigate("/admin");
        })

    }
    changeCategoryNameHandler = (event) => {
        this.setState({categoryName: event.target.value})
    }
    changePriceHandler = (event) => {
        this.setState({price: event.target.value})
    }
    changeProductNameHandler= (event) => {
        this.setState({name: event.target.value});
    }
    changeDetailsHandler= (event) => {
        this.setState({details: event.target.value});
    }

    cancel(){
        this.props.navigate("/admin");
    }
    
    render() {
        return (
            <div>
                <div className='container'>
                    <div className='row'>
                        <div className='card col-md-6 offset-md-3'>
                            <h3 className='text-center'>Add Product</h3>
                            <div className='card-body'>
                                <form>
                                    <div className='form-group'>
                                        <label> Product Name: </label>
                                        <input placeholder='Product Name' name='productName' className='form-control'
                                            value={this.state.name} onChange={this.changeProductNameHandler}/>
                                    </div>
                                    <div className='form-group'>
                                        <label> Details: </label>
                                        <input placeholder='Details' name='Details' className='form-control'
                                            value={this.state.details} onChange={this.changeDetailsHandler}/>
                                    </div>    
                                    <div className='form-group'>
                                        <label> Price: </label>
                                        <input placeholder='Price' name='Price' className='form-control'
                                            value={this.state.price} onChange={this.changePriceHandler}/>
                                    </div>         
                                    <div className='form-group'>
                                        <label> Category Name: </label>
                                        <input placeholder='Category Name' name='Category Name' className='form-control'
                                            value={this.state.categoryName} onChange={this.changeCategoryNameHandler}/>
                                    </div>                   
                                    <button className='btn btn-success' onClick={this.saveProduct}>Save</button>
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

export default withRouter(CreateProductComponent);