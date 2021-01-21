import React, {useState, useEffect} from 'react';
import times from 'lodash/times';
import {MAX_POS} from "../constant";
import {getInitialTileList} from "../util.js/tile";
import useMoveTile from "../hooks/useMoveTile";

export default function Game() {
    const [tileList, setTileList] = useState(getInitialTileList);
    // up, down,left, right
    useMoveTile();
    return (
        <div className="game-container">
            <div className="grid-container">
                {times(MAX_POS, index => (
                    <div key={index} className="grid-row">
                        {times(MAX_POS, index2 => (
                            <div key={index2} className="grid-cell"></div>
                        ))}
                    </div>
                ))}
            </div>
            <div className="tile-container">
                {tileList.map(item => (
                    <div key={item.id} className={`tile tile-${item.value} tile-position-${item.x}-${item.y}`}>
                        <div className="tile-inner">2</div>
                    </div>
                ))}
            </div>
        </div>
    )
    // tile-new
}