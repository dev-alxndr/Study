import React from 'react';
import './Box.css';

export default function Box({size}) {
    if (size == 'big') {
        return <div className="box big">BIGGER 박스</div>
    } else {
        return <div className="box small">SMALLER BOX</div>
    }
};