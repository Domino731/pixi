const TILE_SIZE = 16;
const TILES_X = 8;
const TILES_Y = 11;

class LevelMaker {
    // vector that is representing level size
    levelSize;

    constructor() {
        this.levelSize = {
            x: 100,
            y: 100
        }
        this.init();
    }

    // creating map according to level size
    createMap() {
        const map = document.querySelector("#map")

        // building ground
        for (let i = 0; i < this.levelSize.y; i++) {
            const div = document.createElement('div');
            div.style.width = '16px';
            div.style.height = '16px';
            div.style.transform = `translate(${i * 16}px, 0)`
            div.style.position = 'absolute'
            div.style.top = '0';
            div.style.left = '0';
            map.appendChild(div);
            for (let j = 0; j < this.levelSize.x; j++) {
                const div2 = document.createElement('div');
                div2.style.width = '16px';
                div2.style.height = '16px';
                div2.style.transform = `translate(${i * 16}px, ${j * 16}px)`
                div2.style.position = 'absolute'
                div2.style.top = '0';
                div2.style.left = '0';
                map.appendChild(div2);
            }
        }

    }

    // creating map with sprite tiles
    createPanel() {
        const panel = document.querySelector("#panel");

        for (let i = 0; i < TILES_X; i++) {
            const div = document.createElement('div');
            div.style.width = '16px';
            div.style.height = '16px';
            div.style.transform = `translate(${i * 16}px, 0)`
            div.style.position = 'absolute'
            div.style.top = '0';
            div.style.left = '0';
            panel.appendChild(div);
            for (let j = 0; j < TILES_Y; j++) {
                const div2 = document.createElement('div');
                div2.style.width = '16px';
                div2.style.height = '16px';
                div2.style.transform = `translate(${i * 16}px, ${j * 16}px)`
                div2.style.position = 'absolute'
                div2.style.top = '0';
                div2.style.left = '0';
                panel.appendChild(div2);
            }
        }
    }

    // initialise methods which are responsible for level maker
    init() {
        this.createMap();
        this.createPanel();
    }
}

new LevelMaker();