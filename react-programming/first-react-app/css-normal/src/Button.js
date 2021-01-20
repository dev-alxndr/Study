import React from "react";
import './Button.css';

export default function Button({size}) {
    if (size === 'big') {
        return <button className="button big">BIGGER BUTTON</button>
    } else {
        return <button className="button small">SMALLER BUTTON</button>
    }
};