package oleg.serzhant.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Марс on 23.05.2016.
 */
public class InputHandler {
    public static boolean isSpaceClicked() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }

    public static Vector2 getMousePosition() {
        return new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
    }

    public static boolean isClicked() {
        return Gdx.input.isTouched();
    }


}
