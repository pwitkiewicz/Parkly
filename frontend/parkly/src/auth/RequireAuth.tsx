import React from "react";
import {Navigate} from 'react-router-dom'

const RequireAuth = ({ children }: { children: JSX.Element }) => {
    let auth = true;
    if (!auth) {
        return <Navigate to="/not-authorized" replace />;
    }

    return children;
}

export default RequireAuth;