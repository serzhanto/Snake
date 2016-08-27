package oleg.serzhant.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Марс on 26.05.2016.
 */
public class Bonus {
    Vector2 position;
    Vector2 velocity;
    Random rand = new Random();
    long time;

    public SpriteBatch batch;
    public static Texture texture = new Texture("greenBonus.png");

    public Bonus(Vector2 position, Vector2 velocity) {
        this.position = position;
        this.velocity = velocity;
        batch = new SpriteBatch();
        time = System.currentTimeMillis();

    }

    public void draw() {
        batch.begin();
        position.add(velocity);
        batch.draw(texture, position.x, position.y);
        batch.end();

    }

    public void IdleVelocity() {
        if ((System.currentTimeMillis() - time) > 2500) {
            time = System.currentTimeMillis();
            this.velocity.set(new Vector2(1.0f * (rand.nextFloat() - 0.5f), 1.0f * rand.nextFloat() - 0.5f));
        }
    }


}
