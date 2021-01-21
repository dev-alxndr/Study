import React, {useState, useEffect} from 'react';

export default function Profile({userId}) {
    const [user, setUser] = useState(null);
    useEffect(() => {
        getUserApi(userId).then(data => setUser(data));
    }, [userId]);
    // 유저 아이디가 변경될때만 리로딩
    return (
        <div>
            {!user && <p>loading...</p>}
            {user && (
                <>
                    <p>{`name is ${user.name}`}</p>
                    <p>{`age is ${user.age}`}</p>
                </>
            )}
        </div>
    );
}


const USER1 = { name: 'alxndr', age: 27};
const USER2 = { name: 'suyeon', age: 22};

function getUserApi(userId) {
    return new Promise(res => {
        res(userId % 2 ? USER1 : USER2);
    }, 500);
}