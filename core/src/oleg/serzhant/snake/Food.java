package oleg.serzhant.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Марс on 25.05.2016.
 */
public class Food {
    Vector2 position;
    Vector2 velocity;
    public SpriteBatch batch;
    public static Texture texture = new Texture("blueFood.png");

    public Food(Vector2 position) {
        this.position = position;
        this.velocity = new Vector2(0.0f, 0.0f);
        batch = new SpriteBatch();

    }

    public void draw() {
        batch.begin();
        position.add(velocity);
        batch.draw(texture, position.x, position.y);
        batch.end();

    }

}
