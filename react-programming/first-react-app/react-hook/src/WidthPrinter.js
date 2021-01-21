import React, {useState, useEffect} from 'react';

export default function WidthPrinter() {
    const [width, setWidth] = useState(window.innerWidth);
    useEffect(() => {
        const onResize = () => setWidth(window.innerWidth);
        window.addEventListener('resize', onResize);
        console.log("useEffect 1");
        return () => {
            console.log("useEffect 2");
            window.removeEventListener('resize', onResize);
        };
    }, []);
    // [] => unmount 될때만 새로고침
    // 부수효과 함수안에서 반환하는 함수는 다른 부수효과 함수가 실행되기 전에 실행된다.
    return <div>{`width is ${width}`}</div>
}