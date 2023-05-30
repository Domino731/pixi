import * as PIXI from 'pixi.js';
import {HelloWorld} from './scenes/helloWorld';
import {loadTextures} from "./engine/textures";
import {TilesRenderer} from "./engine/TilesRenderer";
import {Tile} from "./engine/TilesRenderer/Tile";

const TEST_1_SCENE = require('./scenes/TEST_1.json');

console.log(TEST_1_SCENE)

// Main app

export const app = new PIXI.Application();

// Display application properly
document.body.style.margin = '0';
app.renderer.view.style.position = 'absolute';
app.renderer.view.style.display = 'block';

// View size = windows
app.renderer.resize(window.innerWidth, window.innerHeight);
window.addEventListener('resize', () => {
    app.renderer.resize(window.innerWidth, window.innerHeight);
});

// Load assets
loadTextures(app, TEST_1_SCENE).then(() => {
    document.body.appendChild(app.view);

    // Set scene
    // const scene = new HelloWorld(app);
    // console.log('SCENE DATA: ', TEST_1_SCENE)
    new TilesRenderer(TEST_1_SCENE.tiles);
    // app.stage.addChild(scene);
});

