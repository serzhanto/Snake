package oleg.serzhant.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

/**
 * Created by Марс on 23.05.2016.
 */

public class Brick {
    public Vector2 position;
    public Vector2 velocity;
    public LinkedList<Dest> destPosition = new LinkedList<Dest>();
    public SpriteBatch batch;
    public static Texture texture = new Texture("head3.png");


    public Brick(Vector2 position, Vector2 velocity) {
        this.position = position;
        this.velocity = velocity;
        batch = new SpriteBatch();
    }

    public void draw() {
        batch.begin();
        position.add(velocity);

        if (!destPosition.isEmpty()) {

            if (position.x > destPosition.peek().targetPosition.x - 0.005 && position.x < destPosition.peek().targetPosition.x + 0.005 &&
                    position.y > destPosition.peek().targetPosition.y - 0.005 && position.y < destPosition.peek().targetPosition.y + 0.005) {
                velocity.set(destPosition.poll().targetVelocity);

            }
        }


        batch.draw(texture, position.x, position.y);
        batch.end();

    }

    class Dest {
        public Vector2 targetPosition = new Vector2();
        public Vector2 targetVelocity = new Vector2();

        public Dest(Vector2 targetPosition, Vector2 targetVelocity) {
            this.targetPosition = targetPosition;
            this.targetVelocity = targetVelocity;
        }

    }

    public Dest createDest(Vector2 targetPosition, Vector2 targetVelocity) {
        return new Dest(targetPosition, targetVelocity);
    }
}



