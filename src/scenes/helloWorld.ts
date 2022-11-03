import {Application, Container, Sprite} from 'pixi.js';
import {DirectionInitialState} from "./const";

export class HelloWorld extends Container {
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
            app.loader.resources['assets/hello-world.png'].texture
        );

        this.sprite.x = window.innerWidth / 2 - this.sprite.width / 2;
        this.sprite.y = window.innerHeight / 2 - this.sprite.height / 2;
        this.addChild(this.sprite);

        this.initEvents();
        // Handle update
        app.ticker.add(this.update);
    }

    changeDirection(): void {
        document.addEventListener('keypress', ({key}) => {
            switch (key) {
                case 'w':
                    console.log(1);
                    this.direction = {
                        ...DirectionInitialState,
                        top: true
                    }
                    break;
                case 'd':
                    this.direction = {
                        ...DirectionInitialState,
                        right: true
                    }
                    break;
                case 's':
                    this.direction = {
                        ...DirectionInitialState,
                        bottom: true
                    }
                    break;
                case 'a':
                    this.direction = {
                        ...DirectionInitialState,
                        left: true
                    }
                    break;
            }
        });

        document.addEventListener('keyup', ({key}) => {
            if (key === 'w' || key === 'd' || key === 's' || key === 'a') {
                this.direction = {...DirectionInitialState};
            }
        });
    }

    initEvents(): void {
        this.changeDirection();
        console.log(1);
    }

    update(_: any, delta: number) {
        const {left, right, top, bottom} = this.direction;
        if (left) {
            this.state.velocity.x = -1;
        } else if (right) {
            this.state.velocity.x = 1;
        } else {
            this.state.velocity.x = 0;
        }

        if (top) {
            this.state.velocity.y = -1;
        } else if (bottom) {
            this.state.velocity.y = 1;
        } else {
            this.state.velocity.y = 0;
        }

        if (left || right) {
            this.sprite.x += this.state.velocity.x;
        }
        if (top || bottom) {
            this.sprite.y += this.state.velocity.y;
        }

    }
}
