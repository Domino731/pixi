import {Application, Container, Rectangle, Sprite, Texture} from "pixi.js";
import {GROUND_SIZE} from "../../globalConst";
import {Vector} from "../types";

export class Ground extends Container {
    app: Application;

    constructor(app: Application, levelSize: Vector) {
        super();
        this.app = app;

        this.update = this.update.bind(this);

        const Rect: Rectangle = new Rectangle(0, 16, GROUND_SIZE, GROUND_SIZE);
        const texture = Texture.from('assets/tiles_ground_spring.png');
        texture.frame = Rect;

        // building ground
        for (let i = 0; i < levelSize.y; i++) {
            const sprite1 = new Sprite(texture);
            sprite1.scale.set(1, 1);
            sprite1.y = i * 16;
            this.addChild(sprite1);
            for (let j = 0; j < levelSize.x; j++) {
                const sprite2 = new Sprite(texture);
                sprite2.scale.set(1, 1);
                sprite2.y = i * 16;
                sprite2.x = j * 16;
                this.addChild(sprite2);
            }
        }


        app.ticker.add(this.update);
    }

    update(_: any, delta: number) {
    }
}