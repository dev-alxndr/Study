import React, {useState} from 'react';
import Counter from './Counter';
import ReactDOM from 'react-dom';

export default function App() {
  const [color, setColor] = useState('red');

  function onClick() {
    setColor('blue' === color ? 'red' : 'blue');
  }
    /*Fragment는 배열로 넘길때 쓰면 키값을 안적어도 가능*/
    /* <></> 로 표현할 수 있음 */
    return (
        <>
            <p>Hello</p>
            <Counter/>
            {ReactDOM.createPortal(
                <div>
                    <p>PorTAL</p>
                </div>,
                document.getElementById("portal")
            )}
        </>
    );
}


