import React, { Component } from 'react';
import CategoryService from '../Services/CategoryService';
import { withRouter } from '../withRouter';
class ListCategoryComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            categories: []
        }
        this.addCategory = this.addCategory.bind(this);
        this.updateCategory = this.updateCategory.bind(this);
        this.deleteCategory = this.deleteCategory.bind(this);
    }
    componentDidMount(){
        CategoryService.getCategories().then((response) => {
            this.setState({ categories : response.data})
        });
    }
    addCategory(){
        this.props.navigate("/add-category")
    }
    updateCategory(id){
        this.props.navigate("/update-category/${id}");
    }
    deleteCategory(id){
        CategoryService.deleteCategory(id).then(res => {
            this.setState({categories: this.state.customers.filter(category => category.id !== id)});
        });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Category List</h2>
                <div className='row'>
                    <button className='btn btn-primnary' onClick={this.addCategory}> Add Category</button>
                </div>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Category Name</th>
                                <th>Category Details</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.categories.map(
                                    category =>
                                    <tr key={category.id}>
                                        <td>{category.name}</td>
                                        <td>{category.details}</td>
                                        <td>
                                            <button onClick= {() => this.updateCategory(category.id)} className= "btn btn-info">Update</button>
                                            <button onClick= {() => this.deleteCategory(category.id)} className= "btn btn-danger">Delete</button>
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

export default withRouter(ListCategoryComponent);