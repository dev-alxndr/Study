import React, {useState, useEffect} from 'react';

export default function WidthPrinter() {
    const [width, setWidth] = useState(window.innerWidth);
    useEffect(() => {
        const onResize = () => setWidth(window.innerWidth);
        window.addEventListener('resize', onResize);
        return () => {
            window.removeEventListener('resize', onResize);
        };
    }, []);
    // [] => 부수효과가 변경될때만
    // 부수효과 함수안에서 반환하는 함수는 다른 부수효과 함수가 실행되기 전에 실행된다.
    return <div>{`width is ${width}`}</div>
}