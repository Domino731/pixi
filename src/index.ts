import * as PIXI from 'pixi.js';
import {HelloWorld} from './scenes/helloWorld';
import {LevelRender} from "./level/render/levelRender";

// load textures so they will be available for app classes
const load = (app: PIXI.Application) => {
    return new Promise<void>((resolve) => {
        app.loader.add('assets/hello-world.png').add('assets/tiles_ground_spring.png').load(() => {
            resolve();
        });
    });
};

const main = async () => {
    // Main app
    let app = new PIXI.Application();

    // Display application properly
    document.body.style.margin = '0';
    app.renderer.view.style.position = 'absolute';
    app.renderer.view.style.display = 'block';

    // View size = windows
    app.renderer.resize(window.innerWidth, window.innerHeight);
    window.addEventListener('resize', (e) => {
        app.renderer.resize(window.innerWidth, window.innerHeight);
    });

    // Load assets
    await load(app);
    document.body.appendChild(app.view);

    // Set scene
    const scene = new HelloWorld(app);
    const level: LevelRender = new LevelRender(app);
    app.stage.addChild(scene);
    app.stage.addChild(level.ground);
};

main();
