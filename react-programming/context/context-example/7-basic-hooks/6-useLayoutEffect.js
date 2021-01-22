import React, { useState, useEffect, useRef, useLayoutEffect } from 'react';
/*
* useEffect 는 DOM이 렌더링 후 비동기로 호출된다.
* useLayoutEffect 는 동기로 호출되므로 부수효과 함수에서 연산을 많이 하면 브라우저가 먹통이 될수 도있다.
* 대부분 useEffect 를 사용해라.
*
* 렌더링 직후에 DOM요소의 값을 읽는 경우
* 조건에 따라 컴포넌트 다시 렌더링 하고 싶은경우는 useLayoutEffect를 사용.
* */
export default function App() {
    const [width, setWidth] = useState(200);

    /*width가 500이상인경우 500이상값을 렌더링 후 useeffect가 렌더링 되면서 다시 500으로 맞추기 떄문에 깜빡거림
    useEffect(() => {
        if (width > 500) {
            setWidth(500);
        }
    }, [width]);
    */

    /*
    위와 같은 상황에서 useLayoutEffect를 써라~
    렌더링 전에 코드가 실행되기때문에 500이상 되는 사이즈를 그리지않고 바로 500으로 맞춰짐
     */
    const boxRef = useRef();
    useLayoutEffect(() => {
        console.log(boxRef.current.getBoundingClientRect().width);
        if (width > 500) {
            setWidth(500);
        }
        /*
         연산이 많아지면 화면이 느리게뜬다~ 주의하삼~

         let v = 0;
         for (let i = 0; i < 1000000000; i++) {
             v += i *2 + i;
         }
         console.log(v);*/
    }, [width]);

    return (
        <div>
            <div style={{ width, height: 100, backgroundColor: 'green'}} ref={boxRef}>
                test
            </div>
            <button onClick={() => {
                const value = Math.floor(Math.random() * 499 + 1);
                setWidth(value);
            }}>under 500</button>
            <button onClick={() => {
                const value = Math.floor(Math.random() * 500 + 501);
                setWidth(value);
            }}>upper 500</button>
        </div>
    );
}