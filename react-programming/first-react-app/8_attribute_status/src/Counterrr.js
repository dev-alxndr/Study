import React, {useState} from 'react';

export default function Counterr() {
    const [count, setCount] = useState(0);
    function onClick() {
        setCount(count + 1);
    }

    return (
        <div>
            <p>{`현재 카운트 : ${count}` }</p>
            <button onClick={onClick}>INCREASE</button>
        </div>
    )
}