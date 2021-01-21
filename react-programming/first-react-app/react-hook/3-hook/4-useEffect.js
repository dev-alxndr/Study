import React, {useState, useEffect} from 'react';



export default function App() {
    const [count, setCount] = useState(0);
    useEffect(() => {
        document.title = `updated count : ${count}`;
    });
    // 부수효과 함수.
    return <button onClick={() => setCount(count + 1)}>INC</button>
}