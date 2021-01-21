import React, {useState} from 'react';

export default function App() {

    const [todoList, setTodoList] = useState([]);   // useState(초기값) 는 배열을 리턴하는데 [변수, 상태값 변환 함수];
    const [currentId, setCurrentId] = useState(1);
    const [desc, setDesc] = useState('');

    function onAdd() {
        const todo = {id: currentId, desc};
        setCurrentId(currentId +1 );
        setTodoList([...todoList, todo]);
    }

    function onDelete(e) {
        const id = Number(e.target.dataset.id);
        const newTodoList = todoList.filter(todo => todo.id !== id);
        setTodoList(newTodoList)
    }

    function onSaveToServer() {}

    return (
        <div>
            <h3>TO-DO</h3>
            <ul>
                {todoList.map(todo => (
                    <li key={todo.id}>
                        <span>{todo.desc}</span>
                        <button data-id={todo.id} onClick={onDelete}>
                            삭제
                        </button>

                    </li>
                ))}
            </ul>
            <input type="text" value={desc} onChange={e => setDesc(e.target.value)} />
            <button onClick={onAdd}>ADD</button>
            <button onClick={onSaveToServer}>SAVE</button>
        </div>
    );
}
