package oleg.serzhant.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Марс on 23.05.2016.
 */
public class Head {
    public Vector2 position;
    public Vector2 velocity;
    public SpriteBatch batch = new SpriteBatch();
    public static Texture texture = new Texture("head1.png");

    public Head(Vector2 position, Vector2 velocity) {
        this.position = position;
        this.velocity = velocity;

    }

    public void draw() {
        batch.begin();
        position.add(velocity);

        batch.draw(texture, position.x, position.y);
        batch.end();

    }
}
