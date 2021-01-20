import React from 'react';
import Style from './Box.module.scss';

export default function Box({size}) {
    if (size == 'big') {
        return <div className={`${Style.box} ${Style.big}`}>BIGGER 박스</div>
    } else {
        return <div className={`${Style.box} ${Style.small}`}>SMALLER BOX</div>
    }
};