import React, {useState} from 'react';

export default function App() {
    const [name, setName] = useState('');
    const [age, setAge] = useState(0);
    const [v1, setV1] = useState(0);

    return (
        <div>
            <p>{`name is ${name}`}</p>
            <p>{`age is ${age}`}</p>
            <UserEdit
                onSave={() => saveToServer(name, age)}
                setName={setName}
                setAge={setAge}/>
            <p>{`v1 : ${v1}`}</p>
            <button onClick={() => setV1(Math.random())}>Edit V1</button>
        </div>
    )
}

const UserEdit = React.memo(function ({ onSave, setName, setAge }) {

    console.log("userEdit Render");
    return null;
});

function saveToServer(name, age) {}