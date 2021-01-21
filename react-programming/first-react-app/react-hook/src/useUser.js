import {useState, useEffect} from 'react';

export default function useUser(userId) {
    const [user, setUser] = useState(null);
    useEffect(() => {
        getUserApi(userId).then(data => setUser(data));
    }, [userId]);
    return user;

    // 배열은 의존성배열이라한다. 배열이 변경될때만 부수효과함수가 실행된다.
    // 의존성배열에대해 주의할것
}


const USER1 = { name: 'alxndr', age: 27};
const USER2 = { name: 'suyeon', age: 22};

function getUserApi(userId) {
    return new Promise(res => {
        res(userId % 2 ? USER1 : USER2);
    }, 500);
}