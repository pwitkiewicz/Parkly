import {LoginInformation} from "../models/models";
import axios from "axios";

interface AuthService {
    login: (loginInfo: LoginInformation) => void;
    logout: () => void;
    getCurrentUserToken: () => void;
}

const authServer = 'http://localhost:8080'

function createAuthService(): AuthService {

    let data = '';

    const login = async (loginInfo: LoginInformation) => {
        await axios.post(`${authServer}/login`, {
            login: loginInfo.login,
            password: loginInfo.password
        }).then(response => {
            if (response.data) {
                data = response.data;
                sessionStorage.setItem('key', response.data);
            }
        });
        return data;
    }
    const logout = () => {
        sessionStorage.removeItem('key');
    }
    const getCurrentUserToken = () => {
    }
    return {
        login,
        logout,
        getCurrentUserToken
    }
}

const AuthService = createAuthService();

export default AuthService;
