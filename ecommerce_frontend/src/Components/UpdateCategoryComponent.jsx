import React, { Component } from 'react';
import CategoryService from '../Services/CategoryService';
import { withRouter } from '../withRouter';
class UpdateCategoryComponent extends Component {
    constructor(props){
        super(props)
        
        this.state = {
            id: this.props.match.params.id,
            categoryName: "",
            details: "",
        }
        this.changeCategoryNameHandler = this.changeCategoryNameHandler.bind(this);
        this.changeDetailsHandler = this.changeDetailsHandler.bind(this);
        this.updateCategory = this.updateCategory.bind(this);
    }

    componentDidMount(){
        CategoryService.getCategoryById(this.state.id).then((res) => {
            let category  = res.data;
            this.setState({
                categoryName: category.name,
                details: category.details
            });
        });
    }
    updateCategory = (e) => {
        e.preventDefault();
        let category = {name: this.state.categoryName, details: this.state.details};
        console.log("customer => " + JSON.stringify(category));
        CategoryService.updateCategory(category, this.state.id).then( res => {
            this.props.navigate('/categories');
        });


    }
    changeCategoryNameHandler= (event) => {
        this.setState({categoryName: event.target.value});
    }
    changeDetailsHandler= (event) => {
        this.setState({details: event.target.value});
    }
    cancel(){
        this.props.navigate("/categories");
    }
    
    render() {
        return (
            <div>
                <div className='container'>
                    <div className='row'>
                        <div className='card col-md-6 offset-md-3'>
                            <h3 className='text-center'>Update Category</h3>
                            <div className='card-body'>
                                <form>
                                    <div className='form-group'>
                                        <label> Category Name: </label>
                                        <input placeholder='Category Name' name='categoryName' className='form-control'
                                            value={this.state.categoryName} onChange={this.changeCategoryNameHandler}/>
                                    </div>
                                    <div className='form-group'>
                                        <label> Details: </label>
                                        <input placeholder='Details' name='details' className='form-control'
                                            value={this.state.details} onChange={this.changeDetailsHandler}/>
                                    </div>                          
                                    <button className='btn btn-success' onClick={this.updateCategory}>Update</button>
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


export default withRouter(UpdateCategoryComponent);