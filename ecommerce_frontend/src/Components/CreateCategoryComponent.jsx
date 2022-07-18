import React, { Component } from 'react';
import CategoryService from '../Services/CategoryService';
import { withRouter } from '../withRouter';
class CreateCategoryComponent extends Component {
    constructor(props){
        super(props)
        
        this.state = {
            categoryName: "",
            categoryDetails: "",
            
        }
        this.changeCategoryNameHandler = this.changeCategoryNameHandler.bind(this);
        this.changeDetailsHandler = this.changeDetailsHandler.bind(this);
        this.saveCategory = this.saveCategory.bind(this);
    }
    saveCategory = (e) => {
        e.preventDefault();
        let category = {name: this.state.categoryName, details: this.state.categoryDetails};
        console.log("category => " + JSON.stringify(category));

        CategoryService.createCategory(category).then(res =>{
            this.props.navigate("/admin");
        })

    }
    changeCategoryNameHandler= (event) => {
        this.setState({categoryName: event.target.value});
    }
    changeDetailsHandler= (event) => {
        this.setState({categoryDetails: event.target.value});
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
                            <h3 className='text-center'>Add Category</h3>
                            <div className='card-body'>
                                <form>
                                    <div className='form-group'>
                                        <label> Category Name: </label>
                                        <input placeholder='Category Name' name='categoryName' className='form-control'
                                            value={this.state.categoryName} onChange={this.changeCategoryNameHandler}/>
                                    </div>
                                    <div className='form-group'>
                                        <label> Details: </label>
                                        <input placeholder='Details' name='Details' className='form-control'
                                            value={this.state.details} onChange={this.changeDetailsHandler}/>
                                    </div>                           
                                    <button className='btn btn-success' onClick={this.saveCategory}>Save</button>
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

export default withRouter(CreateCategoryComponent);