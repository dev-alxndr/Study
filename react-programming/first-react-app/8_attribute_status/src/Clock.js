import React, {useState, useEffect} from 'react';
import Counterr from "./Counterrr";

export default function Clock() {
    const [seconds, setSeconds] = useState(0);
    useEffect(() => {
        setTimeout(() => {
            setSeconds(v => v + 1);
        }, 1000);
    });

    // key를 변경하면 다른것이라고 판단하고 새롭게 랜더링함
    // Component의 key를 변경하면 삭제되었다가 다시 랜더링됨 그것을 unmount 추가되는것을 mount
    return (
        <div>
            <Counterr key={seconds}/>
            <h1 style={{color: seconds%2 ? 'red' : 'blue'}}>Hello</h1>
            <h2>It's been {seconds} so far.</h2>
        </div>
    )
}