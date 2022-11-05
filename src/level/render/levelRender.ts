import {Application, Container} from "pixi.js";
import {Ground} from "../ground/ground";

export class LevelRender extends Container {
    ground: Ground;

    constructor(app: Application) {
        super();
        this.ground = new Ground(app);
    }
}