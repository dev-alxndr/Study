import React, {useState, useRef, useEffect} from 'react';

export default function App() {
    const timerIdRef = useRef(-1);  // 초깃값 -1
    useEffect(() => {
        timerIdRef.current = setTimeout(() => {}, 1000);
    });

    useEffect(() => {
        if (timerIdRef.current >= 0) {
            clearTimeout(timerIdRef.current);
        }
    });
}