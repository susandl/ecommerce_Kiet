import React, { Component } from 'react';
import CustomerService from '../Services/CustomerService';
import { withRouter } from '../withRouter';
class ListCustomerComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            customers: []
        }
        this.addCustomer = this.addCustomer.bind(this);
        this.updateCustomer = this.updateCustomer.bind(this);
        this.deleteCustomer = this.deleteCustomer.bind(this);
    }
    componentDidMount(){
        CustomerService.getCustomer().then((response) => {
            this.setState({ customers : response.data})
        });
    }
    addCustomer(){
        this.props.navigate("/add-customer")
    }
    updateCustomer(id){
        this.props.navigate("/update-customer/${id}");
    }
    deleteCustomer(id){
        CustomerService.deleteCustomer(id).then(res => {
            this.setState({customers: this.state.customers.filter(customer => customer.id !== id)});
        });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Customer List</h2>
                <div className='row'>
                    <button className='btn btn-primnary' onClick={this.addCustomer}> Add Customer</button>
                </div>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Customer Name</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.customers.map(
                                    customer =>
                                    <tr key={customer.id}>
                                        <td>{customer.name}</td>
                                        <td>
                                            <button onClick= {() => this.updateCustomer(customer.id)} className= "btn btn-info">Update</button>
                                            <button onClick= {() => this.deleteCustomer(customer.id)} className= "btn btn-danger">Delete</button>
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

export default withRouter(ListCustomerComponent);