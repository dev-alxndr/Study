import React, {useMemo, useState} from 'react';

export default function App() {
    const [v1, setV1] = useState(0);
    const [v2, setV2] = useState(0);
    const [v3, setV3] = useState(0);
    const value = useMemo(() => runExpensiveJob(v1, v2), [v1, v2]);

    return (
        <>
            <p>{`value is ${value}`}</p>
            <button onClick={() => {
                setV1(Math.random());
                setV2(Math.random());
            }}>
                V1 / V2 Edit
            </button>
            <p>{`v3 is ${v3}`}</p>
            <button onClick={() => setV3(Math.random())}>edit v3</button>
            </>
    );
}

function runExpensiveJob(v1, v2) {
    console.log("runExpensiveJob is called");
    return v1 + v2;
}