import React from 'react';
import useChangeAppState, {
    STATE_START, STATE_RUNNING
} from "./useChangeAppState";

export default function App() {
    const [state, next] = useChangeAppState(true);
    const msg =
        state === STATE_START ?
            "START APP"
        : state === STATE_RUNNING
        ? 'RUNNING APP'
        : 'STOP APP';

    return (
        <div>
            <p>{msg}</p>
            <button onClick={next}>next</button>
        </div>
    )
}