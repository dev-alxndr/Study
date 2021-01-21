import React, {useState} from 'react';
import Title from './Title'

export default function Counter() {
    const [count, setCount] = useState({value: 0});
    function onClick() {
        setCount({...count, value: count.value + 1});
    }

    return (
        <div>
            <Title title={`현재카운트: ${count.value}`}></Title>
            <button onClick={onClick}>INCREASE</button>
        </div>
    )
}