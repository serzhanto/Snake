package oleg.serzhant.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Марс on 27.05.2016.
 */
public class BonusGenerator {
    private static Random rand = new Random();

    public static Bonus next() {
        return new Bonus(new Vector2(rand.nextInt(Gdx.graphics.getWidth() - 30), rand.nextInt(Gdx.graphics.getHeight() - 30)),
                new Vector2(1.0f * (rand.nextFloat() - 0.5f), 1.0f * rand.nextFloat() - 0.5f));

    }

}
