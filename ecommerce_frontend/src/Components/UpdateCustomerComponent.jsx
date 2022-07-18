import React, { Component } from 'react';
import CustomerService from '../Services/CustomerService';
import { withRouter } from '../withRouter';
class UpdateCustomerComponent extends Component {
    constructor(props){
        super(props)
        
        this.state = {
            id: this.props.match.params.id,
            customerName: "",
            password: "",
        }
        this.changeCustomerNameHandler = this.changeCustomerNameHandler.bind(this);
        this.changePasswordHandler = this.changePasswordHandler.bind(this);
        this.updateCustomer = this.updateCustomer.bind(this);
    }

    componentDidMount(){
        CustomerService.getCustomerById(this.state.id).then((res) => {
            let customer  = res.data;
            this.setState({
                customerName: customer.name,
                password: customer.pass
            });
        });
    }
    updateCustomer = (e) => {
        e.preventDefault();
        let customer = {username: this.state.customerName, password: this.state.password};
        console.log("customer => " + JSON.stringify(customer));
        CustomerService.updateCustomer(customer, this.state.id).then( res => {
            this.props.navigate('/home');
        });


    }
    changeCustomerNameHandler= (event) => {
        this.setState({customerName: event.target.value});
    }
    changePasswordHandler= (event) => {
        this.setState({password: event.target.value});
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
                            <h3 className='text-center'>Update Customer</h3>
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
                                    <button className='btn btn-success' onClick={this.updateCustomer}>Update</button>
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


export default withRouter(UpdateCustomerComponent);