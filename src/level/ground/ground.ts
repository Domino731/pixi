import {Application, Container, Sprite} from "pixi.js";
import {DirectionInitialState} from "../../scenes/const";

export class Ground extends Container {
    app: Application;
    sprite: Sprite;
    state: { velocity: { x: number; y: number } };
    direction: {
        top: boolean;
        right: boolean;
        bottom: boolean;
        left: boolean;
    }

    constructor(app: Application) {
        super();
        // set class keys
        this.app = app;
        this.state = {velocity: {x: 1, y: 1}};
        this.direction = {...DirectionInitialState}

        this.update = this.update.bind(this);


        // create sprite
        this.sprite = new Sprite(
            app.loader.resources['assets/tiles_ground_spring.png'].texture
        );

        this.sprite.x = window.innerWidth / 2 - this.sprite.width / 2;
        this.sprite.y = window.innerHeight / 2 - this.sprite.height / 2;
        this.addChild(this.sprite);

        app.ticker.add(this.update);
    }

    update(_: any, delta: number) {
    }

}