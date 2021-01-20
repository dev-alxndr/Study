import React from 'react';
import Style from './Button.module.css';
import cn from 'classnames';

export default function Button({size}) {

    const isBig = size === 'big';

    return (
        <button
            className={cn(Style.button, {
                [Style.big]: isBig,
                [Style.small]: !isBig,
            })}>
            {isBig ? 'BIG' : 'SMALL'}
        </button>
    )

    // if (size === 'big') {
    //     return <button className={`${Style.button} ${Style.big}`}>Big Button</button>;
    // } else {
    //     return (
    //     <button className={cn(Style.button, Style.small)}>Small Button</button>
    //     );
    // }
};