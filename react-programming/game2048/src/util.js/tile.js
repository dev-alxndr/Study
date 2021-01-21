import {getRandomInteger} from "./number";
import {MAX_POS} from "../constant";

export function getInitialTileList() {
    const tileList = [];
    const tile1 = makeTile(tileList);
    tileList.push(tile1);
    const tile2 = makeTile(tileList);
    tileList.push(tile2);
    return tileList;
}


function checkCollision(tileList, tile) {
    return tileList.some(item => item.x === tile.x && item.y === tile.y);
}

let currentId = 0;
export function makeTile(tileList) {
    let tile;
    while(!tile || checkCollision(tileList, tile)) {
        tile = {
            id: currentId++,
            x: getRandomInteger(1, MAX_POS),
            y: getRandomInteger(1, MAX_POS),
            value: 2
        }
    }
    return tile;
}


