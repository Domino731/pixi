// temporary name
import {Vector} from "../types";

export class MainLevel {
    private readonly groundData: Array<Vector>;

    constructor(levelSize: Vector) {
        this.groundData = [];
    }
}