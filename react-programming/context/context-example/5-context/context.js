import React, { createContext, useState, useContext } from 'react';

const UserContext = createContext('unknown');

export default function App() {
    const [name, setName] = useState('alxndr');
    return (
        <div>
            <UserContext.Provider value={name}>
                <div>상단 메뉴</div>
                <Profile/>
                <div>하단 메뉴</div>
                <input type="text" value={name} onChange={e => setName(e.target.value)}/>
            </UserContext.Provider>
        </div>
    );
}

const Profile = React.memo(function () {
    console.log("Profile rendered")
    return (
        <div>
            <Greeting/>
            {/* ... */}
        </div>
    );
});

// useCOntext
function Greeting() {
    const username = useContext(UserContext);
    // render props..? pattern
    return <p>{`${username}님 하이`}</p>;
}

