package oleg.serzhant.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Game extends ApplicationAdapter {
    Snake snake;
    ArrayList<Food> food;
    ArrayList<Bonus> bonus;
    BitmapFont font;
    CharSequence str;
    SpriteBatch spriteBatch;
    Texture gameOver;
    static boolean isGameOver = false;
    static float speed = 2.5f;
    static int score = 0;
    static int maxBonus = 5;
    static int maxFood = 25;


    @Override
    public void create() {
        snake = new Snake();
        food = new ArrayList<Food>();
        Random rand = new Random();
        gameOver = new Texture("gameOver.png");

        for (int i = 0; i < maxFood; i++) {
            food.add(FoodGenerator.next());
        }
        bonus = new ArrayList<Bonus>();
        for (int i = 0; i < maxBonus; i++) {
            bonus.add(new Bonus(new Vector2(rand.nextInt(Gdx.graphics.getWidth() - 30), rand.nextInt(Gdx.graphics.getHeight() - 30)),
                    new Vector2(1.0f * (rand.nextFloat() - 0.5f), 1.0f * rand.nextFloat() - 0.5f)));
        }

        font = new BitmapFont();
        spriteBatch = new SpriteBatch();
        isGameOver = false;
        score = 0;
    }

    @Override
    public void render() {
        if (!isGameOver) {
            Gdx.gl.glClearColor(200 / 255.0f, 191 / 255.0f, 231 / 255.0f, 1f);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            snake.update();
            snake.draw();
            for (Food oneFood : food) {
                oneFood.velocity.scl(0.958f);
                if (oneFood.position.cpy().sub(snake.head.position).len() < 40) {
                    oneFood.velocity = oneFood.position.cpy().sub(snake.head.position).nor().scl(-1.0f);
                }
                oneFood.draw();
            }

            Iterator<Food> itr = food.listIterator();
            while (itr.hasNext()) {
                Food oneFood = itr.next();
                if (oneFood.position.x > snake.head.position.x &&
                        oneFood.position.x < snake.head.position.x + snake.head.texture.getWidth() &&
                        oneFood.position.y > snake.head.position.y &&
                        oneFood.position.y < snake.head.position.y + snake.head.texture.getHeight()) {
                    itr.remove();
                    score += 1;
                    snake.addBody();
                }
            }

            Iterator<Bonus> itr2 = bonus.iterator();
            while (itr2.hasNext()) {
                Bonus oneBonus = itr2.next();
                if (oneBonus.position.x > snake.head.position.x &&
                        oneBonus.position.x < snake.head.position.x + snake.head.texture.getWidth() &&
                        oneBonus.position.y > snake.head.position.y &&
                        oneBonus.position.y < snake.head.position.y + snake.head.texture.getHeight()) {
                    itr2.remove();
                    score += 100;
                    for (int i = 0; i < 3; i++) {
                        snake.addBody();

                    }
                }
            }


            for (Bonus oneBonus : bonus) {
                //oneBonus.velocity.scl(0.998f);
                if (oneBonus.position.cpy().sub(snake.head.position).len() < 150) {
                    oneBonus.velocity = oneBonus.position.cpy().sub(snake.head.position).nor().scl(0.7f * Game.speed);
                } else {

                    oneBonus.IdleVelocity();
                }


                if (oneBonus.position.x > Gdx.graphics.getWidth()) oneBonus.position.x = 0;
                if (oneBonus.position.x < 0) oneBonus.position.x = Gdx.graphics.getWidth();
                if (oneBonus.position.y > Gdx.graphics.getHeight()) oneBonus.position.y = 0;
                if (oneBonus.position.y < 0) oneBonus.position.y = Gdx.graphics.getHeight();

                oneBonus.draw();
            }

            if (bonus.size() < maxBonus) {
                for (int i = 0; i < maxBonus - bonus.size(); i++) {
                    bonus.add(BonusGenerator.next());
                }


            }


            if (food.size() < maxFood) {
                for (int i = 0; i < maxFood - food.size(); i++) {
                    food.add(FoodGenerator.next());
                }


            }


            spriteBatch.begin();
            str = "Score: " + score;
            font.draw(spriteBatch, str, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 10);
            spriteBatch.end();


        } else {
            spriteBatch.begin();
            str = "score: " + score;
            spriteBatch.draw(gameOver, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 2);
            font.draw(spriteBatch, str, 400, 400);
            spriteBatch.end();

            if (InputHandler.isSpaceClicked()) {
                create();
            }
        }

    }
}
