import React, { forwardRef, useState, useImperativeHandle} from 'react';

function Profile(_, ref) {
    const [name, setName] = useState('alxndr');
    const [age, setAge] = useState(0);

    useImperativeHandle(ref, () => ({
        addAge: value => setAge(age + value),
        getNameLength: () => name.length,
    }));

    return (
        <div>
            <p>{`name is ${name}`}</p>
            <p>{`age is ${age}`}</p>
            {/* ... */}
        </div>
    )
}

export default forwardRef(Profile);