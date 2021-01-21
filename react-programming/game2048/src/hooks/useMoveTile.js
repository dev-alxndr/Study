import {addKeyObserver, removeKeyObserver} from "../util.js/keyboard";
import {useEffect} from 'react';
import {makeTile} from "../util.js/tile";

export default function useMoveTile({ tileList, setTileList }) {

    function moveAndAdd(x, y) {
        const newTileList = moveTile({tileList, x, y});
        const newTile = makeTile(newTileList);
        newTileList.push(newTile);
        setTileList(newTileList);
    }

    function moveUp(){
        moveAndAdd({x: 0, y: -1})
    };
    function moveDown(){
        moveAndAdd({x: 0, y: 1})
    };
    function moveLeft(){
        moveAndAdd({x: -1, y: 0})
    };
    function moveRight(){
        moveAndAdd({x: 1, y: 0})
    };


    useEffect(() => {
        addKeyObserver('up', () => {
            console.log("UP!!!!!!!");
        });
        addKeyObserver('down', () => {
            console.log("DOWNNNNNN");
        });
        addKeyObserver('right', () => {});
        addKeyObserver('left', () => {});
        return () => {
            removeKeyObserver('up', moveUp);
            removeKeyObserver('down', moveDown);
            removeKeyObserver('right', moveRight);
            removeKeyObserver('left', moveLeft);

        }
    });
}