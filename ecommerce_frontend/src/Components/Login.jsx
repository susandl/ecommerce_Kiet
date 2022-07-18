import React, { Component } from 'react';
import AuthService from '../Services/AuthService';
import { withRouter } from '../withRouter';
class Login extends Component {
    constructor(props){
        super(props);
        this.state = {
            name:"",
            pass:""
        }
        this.loginhandler=this.loginhandler.bind(this);
        this.register=this.register.bind(this);
        this.changeNameHandler=this.changeNameHandler.bind(this);
        this.changePassHandler=this.changePassHandler.bind(this);
    }
    loginhandler(e){
        e.preventDefault();
        console.log(this.state.name,this.state.pass)
        AuthService.login(this.state.name,this.state.pass).then(
            (data) => {
                console.log("Return")
                console.log(data.roles[0])
                if(data.roles[0]==='ROLE_ADMIN'){
                    console.log('equal')
                    this.props.navigate("/admin")
                }
                if(data.roles[0]==='ROLE_CUSTOMER'){
                    this.props.navigate("/homepage")
                }
                
            }
        )
    }
    register(){
        AuthService.register(this.state.name,this.state.pass).then(
            () => alert("registered")
        )
    }
    changeNameHandler= (event) => {
        this.setState({name: event.target.value});
    }
    changePassHandler= (event) => {
        this.setState({pass: event.target.value});
    }
    render() {
        return (
            <div>
                <div className='container'>
                <div className='card col-md-6 offset-md-3'>
                            <h3 className='text-center'>Login form</h3>
                            <div className='card-body'>
                                <form>
                                    <div className='form-group'>
                                        <label> User Name: </label>
                                        <input placeholder='User Name' name='UserName' className='form-control'
                                            value={this.state.name} onChange={this.changeNameHandler}/>
                                    </div>
                                    <div className='form-group'>
                                        <label> User Password: </label>
                                        <input placeholder='Password' name='UserPass' className='form-control'
                                            value={this.state.pass} onChange={this.changePassHandler}/>
                                    </div>                                                                                         
                                    <button className='btn btn-success' onClick={this.loginhandler}>Sign in</button>
                                    <button className='btn btn-primary' onClick={this.register}>Sign up</button>
                                </form>
                            </div>
                        </div> 
                        </div>
            </div>
        );
    }
}

export default withRouter(Login);