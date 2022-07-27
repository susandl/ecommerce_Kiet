import React, {useEffect, useState} from 'react';
import LoginService from '../../service/LoginService';
import { useHistory } from "react-router-dom";
import "./login.css";
import styled from 'styled-components';
const SignInPage = styled.div`
    display: flex;
    width: 100%;
    height: 600px;
    align-items: center;
    justify-content: center;
    background-color: #ffffff;

`

const Container = styled.div`
    display: flex;
    flex-direction: column;
    width: 500px;
    height: 300px;
    justify-content: center;
    align-items: center;
`

const H2 = styled.h2`
    display: flex;
    align-items: center;
    justify-content: center;
    margin-bottom: 0.8rem;
    color: var(--color-primary);
    border: 2px solid #333;
    width: 100%;
    height: 100px;
    margin: 0;
    border-radius: 5px;
    background-color: #eff1f5;
`

const Form = styled.form`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-end;
    width: 100%;
    height: 100%;
    border: 2px solid #333;
    border-radius: 5px;
    padding: 20px 20px 10px 90px;
`

const Input = styled.input`
    width: 220px;
    height: 40px;
    margin: 5px 20px;
    border: 1px solid #333;
    outline: none;
    color: #191919;
    border-radius: 10px;
    padding: 0px 10px;
    box-sizing: border-box;
    &::placeholder {
        letter-spacing: 2px;

        font-size: 15px;
    }
    &:focus {
        box-shadow: 2px 2px 30px rgba(0, 0, 0, 0.1);
    }
`

// const Button = styled.button`
//     cursor: not-allowed;
//     width: 220px;
//     height: 40px;
//     margin: 10px 0px;
//     border: none;
//     outline: none;
//     border-radius: 20px;
//     box-shadow: 12px 12px 30px rgba(0, 0, 0, 0.1);
//     background-color: var(--color-primary);
//     color: #ffffff;
//     font-size: 1.1rem;
//     &:active {
//         transform: scale(1.05);
//     }
//     &:hover {
//         background-color: rgb(207, 60, 60);
//         transition: all ease 0.3s;
//     }
// `

const Label = styled.label`
display: flex;
width: 100%;
justify-content: space-between;
    font-size: 1.2rem;

`

const Title = styled.div`
    font-size: 1.2rem;
    display: flex;
    justify-content: center;
    align-items: center;

`

const LoginPage = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [isLoginFail, setIsLoginFail] = useState(false);
    const [isAdmin, setIsAdmin] = useState(false);
    let history = useHistory();
    const handleLogin = (e) => {
        e.preventDefault();
        login(username, password);
    }
    
    const handleusername = (e) => {
        setUsername(e.target.value);
        console.log(username);
    }
    const handlepassword = (e) => {
        setPassword(e.target.value);
        console.log(password);
    }


    const login = (username, password) => {
        console.log(username,password)
        LoginService.login(username, password)
            .then(res => {
                console.log(res)
                window.localStorage.setItem('accessToken', res.data.accessToken)
                localStorage.setItem('user', JSON.stringify(res.data));
                if(res.data.roles[0]=='ROLE_ADMIN'){
                history.push("../", {replace: true});
                }
                else{
                    setIsAdmin(true);
                    localStorage.removeItem('user');
                    localStorage.removeItem('accessToken');
                }
            })
            .catch(errors => {
                setIsLoginFail(true)
                if (errors){
                    console.log(errors)
                }
            })
    }


    return (
        <div>
            <SignInPage>
                <Container>
                    <Form>
                        <Label>
                            <Title>Username:</Title>
                            <Input id="usernameLogin" type="text"
                                   onChange={(e) => {
                                    handleusername(e);
                                    setIsLoginFail(false);
                                   }}
                            />
                        </Label>
                        <Label>
                            <Title>Password:</Title>
                            <Input id="passwordLogin" type="password"
                                   onChange={(e) => {
                                       handlepassword(e);
                                       setIsLoginFail(false);
                                   }}
                            />
                        </Label>
                        <p className={"text-danger mt-2 fs-6 d-lg-none" + (isLoginFail ? 'd-lg-none' : '')}>Username
                            or password is incorrect. Please try again</p>
                            <p className={"text-danger mt-2 fs-6 d-lg-none" + (isAdmin ? 'd-lg-none' : '')}>Not admin account. Please try again</p>
                        <button className='btn btn-primary'
                            onClick={(e) => handleLogin(e)}>
                            Login
                        </button>
                        {/* {error && <Error>Something went wrong...</Error>} */}
                    </Form>
                </Container>
            </SignInPage>
        </div>
    )
}
export default LoginPage