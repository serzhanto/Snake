package oleg.serzhant.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Марс on 25.05.2016.
 */
public class FoodGenerator {
    private static Random rand = new Random();

    public static Food next() {
        return new Food(new Vector2(rand.nextInt(Gdx.graphics.getWidth() - 30), rand.nextInt(Gdx.graphics.getHeight() - 30)));

    }


}
