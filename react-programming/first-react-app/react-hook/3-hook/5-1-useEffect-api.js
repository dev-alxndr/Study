import React, {useState, useEffect} from 'react';

export default function Profile({userId}) {
    const [user, setUser] = useState(null);
    useEffect(() => {
        getUserApi(userId).then(data => setUser(data));
    }, [userId]);
    // 배열은 의존성배열이라한다. 배열이 변경될때만 부수효과함수가 실행된다.

    return (
        <div>
            {!user && <p>사용자 정보를 가져오는 중....</p>}
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
    return new Promise(() => {
        res(userId % 2 ? USER1 : USER2);
    }, 500);
}