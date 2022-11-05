import {Application, Container} from "pixi.js";
import {Ground} from "../ground/ground";
import {Vector} from "../types";
import {GROUND_SIZE} from "../../globalConst";

export class LevelRender extends Container {
    ground: Ground;
    private readonly levelSize: Vector;

    constructor(app: Application) {
        super();
        // set level size according to full hd resolution
        this.levelSize = {
            x: 1920 / GROUND_SIZE,
            y: 1080 / GROUND_SIZE
        }
        
        this.ground = new Ground(app, this.levelSize);
    }
}