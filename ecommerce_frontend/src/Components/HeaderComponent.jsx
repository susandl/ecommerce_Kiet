import React, { Component } from 'react';
import AuthService from '../Services/AuthService';
import { withRouter } from '../withRouter';
import {Link } from 'react-router-dom';
class HeaderComponent extends Component {
    constructor(props){
        super(props);
        this.state = {

        }
        
    }
    

    
    render() {
        return (
            <div>
                <header>
                    <nav className='navbar navbar-expand-md navbar-dark bg-dark'>
                        <div><h2 className='navbar-brand'>Admin Ecommerce Management</h2></div>
                        <Link to="/customers"> customers </Link>
                         <Link to="/categories"> categories </Link>
                         <Link to="/products"> products </Link>
                         <Link to="/login"> logout </Link>

                    </nav>
                </header>
            </div>
        );
    }
}

export default withRouter(HeaderComponent);