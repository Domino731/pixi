import {Application, Container, Rectangle, Sprite, Texture} from "pixi.js";
import {DirectionInitialState} from "../../scenes/const";
import {GROUND_SIZE} from "../../globalConst";
import {Vector} from "../types";

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

    constructor(app: Application, levelSize: Vector) {
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

        this.sprite.x = 0;
        this.sprite.y = 0;
        this.sprite.scale.set(2, 2);

        const Rect: Rectangle = new Rectangle(0, 0, GROUND_SIZE, GROUND_SIZE);
        const texture = Texture.from('assets/tiles_ground_spring.png');
        texture.frame = Rect;
        // building ground
        for (let i = 0; i < levelSize.y; i++) {
            const sprite1 = new Sprite(texture);
            sprite1.scale.set(1, 1);
            sprite1.y = i * 16;
            // sprite1.y = i * 16
            this.addChild(sprite1);
            for (let j = 0; j < levelSize.x; j++) {
                const sprite2 = new Sprite(texture);
                sprite2.scale.set(1, 1);
                sprite2.y = i * 16;
                sprite2.x = j * 16;
                // sprite1.y = i * 16
                this.addChild(sprite2);
            }
        }


        app.ticker.add(this.update);
    }

    update(_: any, delta: number) {
    }

}