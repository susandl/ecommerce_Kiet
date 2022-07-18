import React, { Component } from 'react';
import CustomerService from '../Services/CustomerService';
import { withRouter } from '../withRouter';
class CreateCustomerComponent extends Component {
    constructor(props){
        super(props)
        
        this.state = {
            customerName: "",
            password: "",
            role: ""
        }
        this.changeCustomerNameHandler = this.changeCustomerNameHandler.bind(this);
        this.changePasswordHandler = this.changePasswordHandler.bind(this);
        this.changeRoleHandler = this.changeRoleHandler.bind(this);
        this.saveCustomer = this.saveCustomer.bind(this);
    }
    saveCustomer = (e) => {
        e.preventDefault();
        let customer = {username: this.state.customerName, password: this.state.password,role:this.state.role};
        console.log("customer => " + JSON.stringify(customer));

        CustomerService.createCustomer(customer).then(res =>{
            this.props.navigate("/admin");
        })

    }
    changeCustomerNameHandler= (event) => {
        this.setState({customerName: event.target.value});
    }
    changePasswordHandler= (event) => {
        this.setState({password: event.target.value});
    }
    changeRoleHandler= (event) => {
        this.setState({role: event.target.value});
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
                            <h3 className='text-center'>Add Customer</h3>
                            <div className='card-body'>
                                <form>
                                    <div className='form-group'>
                                        <label> Customer Name: </label>
                                        <input placeholder='Customer Name' name='customerName' className='form-control'
                                            value={this.state.customerName} onChange={this.changeCustomerNameHandler}/>
                                    </div>
                                    <div className='form-group'>
                                        <label> Password: </label>
                                        <input placeholder='Password' name='password' className='form-control'
                                            value={this.state.password} onChange={this.changePasswordHandler}/>
                                    </div>
                                    <div className='form-group'>
                                        <label> Role: </label>
                                        <input placeholder='Role' name='role' className='form-control'
                                            value={this.state.role} onChange={this.changeRoleHandler}/>
                                    </div>
                                    <button className='btn btn-success' onClick={this.saveCustomer}>Save</button>
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

export default withRouter(CreateCustomerComponent);