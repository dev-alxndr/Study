import React from 'react';
import {BrowserRouter, Route, Link} from 'react-router-dom';
import Rooms from "./Rooms";

export default function App() {
    return (
        <BrowserRouter>
            <div style={{padding: 20, border: '5px solid gray'}}>
                <Link to="/">HOME</Link>
                <br/>
                <Link to="/photo">PHOTO</Link>
                <br/>
                <Link to="/rooms">ROOMS</Link>
                <br/>
                <Route exact path="/" componet={Home} />
                <Route exact path="/photo" componet={Photo} />
                <Route exact path="/rooms" componet={Rooms} />
            </div>
        </BrowserRouter>
    );
};


function Home() {
    return <h2>This is HOMEPAGE</h2>;
}
function Photo() {
    return <h2>YOU CAN SEE PHOTOS.</h2>
}
