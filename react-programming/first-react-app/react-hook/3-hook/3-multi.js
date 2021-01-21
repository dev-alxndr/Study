import React, {useState} from 'react';

export default function App() {
    const [state, setState] = useState({name: '', age: 0});
    return (
        <div>
            <p>{`name is ${state.name}`}</p>
            <p>{`age is ${state.age}`}</p>
            <input type="text" value={state.name} onChange={e => setState({...state, name: e.target.value})}/>
            <input type="number" value={state.age} onChange={e => setState({...state, age: e.target.value})}/>
        </div>
    );
};
