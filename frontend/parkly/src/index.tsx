import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter, Routes, Route} from "react-router-dom";

import './index.css';
import App from './app/App';
import reportWebVitals from './reportWebVitals';
import NotFoundPage from "./pages/notFoundPage/NotFoundPage";
import RequireAuth from "./auth/RequireAuth";
import NotAuthorizedPage from "./pages/notAuthorizedPage/NotAuthorizedPage";
import BookingsPage from './pages/bookingsPage/BookingsPage';
import SettingsPage from "./pages/settingsPage/SettingsPage";
import LogoutPage from "./pages/logoutPage/LogoutPage";

ReactDOM.render(
    <React.StrictMode>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={
                    <RequireAuth>
                        <App/>
                    </RequireAuth>}
                />
                <Route path="/settings" element={
                    <RequireAuth>
                        <SettingsPage/>
                    </RequireAuth>}
                />
                <Route path="/parking-spots" element={
                    <RequireAuth>
                        <App/>
                    </RequireAuth>}
                />
                <Route path="/bookings" element={
                    <RequireAuth>
                        <>
                        <BookingsPage/>
                        </>
                    </RequireAuth>}
                />
                <Route path="/not-authorized" element={<NotAuthorizedPage/>}/>
                <Route path="/logout" element={<LogoutPage/>}/>
                <Route path="*" element={<NotFoundPage/>}/>
            </Routes>
        </BrowserRouter>
    </React.StrictMode>,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
