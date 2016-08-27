package oleg.serzhant.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Марс on 23.05.2016.
 */

public class Snake {
    public Head head;
    private ArrayList<Brick> neck;
    private LinkedList<Brick> brick;

    public Snake() {
        head = new Head(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), new Vector2(-1.0f * Game.speed, 0.0f));
        neck = new ArrayList<Brick>();
        for (int i = 1; i < 2; i++) {
            neck.add(new Brick(head.position.cpy().sub(i * -25, 0), head.velocity.cpy()));
        }

        brick = new LinkedList<Brick>();
        brick.add(new Brick(head.position.cpy().sub(2 * -25, 0), head.velocity.cpy()));


    }

    public void addBody() {
        brick.add(new Brick(brick.get(brick.size() - 1).position.cpy().sub(brick.get(brick.size() - 1).velocity.cpy().scl(10)), brick.get(brick.size() - 1).velocity.cpy()));
        brick.get(brick.size() - 1).destPosition.addAll(brick.get(brick.size() - 2).destPosition);
    }

    public void positionToQueue() {
        for (Brick queue : brick) {
            queue.destPosition.add(queue.createDest(head.position.cpy(), head.velocity.cpy()));
        }
        for (Brick queue : neck) {
            queue.destPosition.add(queue.createDest(head.position.cpy(), head.velocity.cpy()));
        }

    }

    public void update() {

        if (InputHandler.isClicked()) {
            if (InputHandler.getMousePosition().x < Gdx.graphics.getWidth() - 40 && InputHandler.getMousePosition().x > 40
                    && InputHandler.getMousePosition().y < Gdx.graphics.getHeight() - 40 && InputHandler.getMousePosition().y > 40) {
                head.velocity = head.position.cpy().sub(InputHandler.getMousePosition()).nor().scl(-1.0f * Game.speed);
                positionToQueue();

            }
        }
        if (head.position.x + head.texture.getWidth() >= Gdx.graphics.getWidth() || head.position.x <= 0) {
            head.velocity.nor().scl(-1.0f * Game.speed, 1.0f * Game.speed);
            positionToQueue();
        }
        if (head.position.y + head.texture.getHeight() >= Gdx.graphics.getHeight() || head.position.y <= 0) {
            head.velocity.nor().scl(1.0f * Game.speed, -1.0f * Game.speed);
            positionToQueue();
        }

        for (Brick bricks : brick) {

            if (head.position.x > bricks.position.x &&
                    head.position.x < bricks.position.x + 15 &&
                    head.position.y > bricks.position.y &&
                    head.position.y < bricks.position.y + 15) {
                Game.isGameOver = true;
            }
        }
    }


    public void draw() {

        for (Brick queue : neck) {
            queue.draw();
        }

        for (Brick queue : brick) {
            queue.draw();
        }

        head.draw();

    }

}
