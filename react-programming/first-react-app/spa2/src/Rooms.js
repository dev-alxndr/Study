import React from 'react';
import {Route, Link} from 'react-router-dom';


export default function Rooms({match}) {
    return (
        <div>
            <h2>introduce our Rooms.</h2>
            <Link to={`${match.url}/blueRoom`}>Blue Room</Link>
            <br/>
            <Link to={`${match.url}/greenRoom`}>Green Room</Link>
            <br/>
            <Route path={`${match.url}/:roomId`} component={Room}/>
            <Route
                exact
                path={match.url}
                render={() => <h3>select room.</h3>}/>
        </div>
    );
};

function Room({match}) {
    return <h2>{`you selected room number ${match.params.roomId}`}</h2>;
}


