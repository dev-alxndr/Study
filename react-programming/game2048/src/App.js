import React from 'react';
import Header from "./components/Header";
import AboveGame from "./components/AboveGame";
import Game from "./components/Game";

export default function App() {

    return (
        <div className="container">
            <Header/>
            <Game/>
            <AboveGame/>
        </div>
    )
}

