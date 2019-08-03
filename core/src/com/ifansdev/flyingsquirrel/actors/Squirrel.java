package com.ifansdev.flyingsquirrel.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ifansdev.flyingsquirrel.MyGame;
import com.ifansdev.flyingsquirrel.assets.Assets;

public class Squirrel extends Actor {

    private TextureRegion squirrelSpin;
    private TextureRegion squirrelDown;
    private TextureRegion squirrelTailMedium;
    private Animation<TextureRegion> squirrelFlyAnimation;

    private float animationTime;
    private float rotateSquirrelSpin;

    private Circle boundCircle;
    private Rectangle boundRectangleFly;
    private Rectangle boundRectangleDown;

    private int yPosition;
    private int gravity;
    private int velocity;

    private boolean twoTouch;

    public Squirrel(MyGame myGame) {

        squirrelSpin   = myGame.getAssets().getTexture(Assets.SQUIRREL_SPIN);
        squirrelDown   = myGame.getAssets().getTexture(Assets.SQUIRREL_DOWN);
        squirrelTailMedium = myGame.getAssets().getTexture(Assets.SQUIRREL_TAIL_MEDIUM);

        TextureRegion[] squirrelFly = myGame.getAssets().getSquirrelFly();
        squirrelFlyAnimation = new Animation<>(0.1f, squirrelFly);
        squirrelFlyAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        setX(35);
        setY(68);
        yPosition = (int) getY();
        gravity = -13;

        boundCircle = new Circle(47, getY(), (squirrelSpin.getRegionWidth() / 2f) - 1);

        boundRectangleFly = new Rectangle(39, getY(), squirrelTailMedium.getRegionWidth()
                - squirrelTailMedium.getRegionWidth() / 3f, squirrelTailMedium.getRegionHeight());

        boundRectangleDown = new Rectangle(46, getY(), squirrelDown.getRegionWidth() / 2f,
                squirrelDown.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        if (getY() > yPosition) velocity += gravity;
        setY(getY() + velocity * delta);

        if (getY() < yPosition) {
            gravity = -13;
            setY(yPosition);
        }

        rotateSquirrelSpin -= 1000 * delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (gravity == -13) {
            batch.draw(squirrelSpin, getX(), getY(),
                    squirrelSpin.getRegionWidth() / 2f, squirrelSpin.getRegionHeight() / 2f,
                    squirrelSpin.getRegionWidth(), squirrelSpin.getRegionHeight(), 1,1, rotateSquirrelSpin);
        }

        if (gravity == -1) {
            TextureRegion currentFrame = squirrelFlyAnimation.getKeyFrame(animationTime, true);
            batch.draw(currentFrame, getX() - 10, getY());
            animationTime += Gdx.graphics.getDeltaTime();

        }

        if (gravity == -110) {
            batch.draw(squirrelDown, getX(), getY());
        }
    }

    public void onClick() {
        if (getY() <= yPosition) {
            gravity = -13;
            velocity = 350;
            twoTouch = false;
        }

        if (twoTouch) gravity = -110;

        if (getY() > yPosition && !twoTouch) {
            gravity = -1;
            velocity = 40;
            twoTouch = true;
        }
    }

    public boolean collides(Rectangle boundStone) {
        if (gravity == -13) {
            boundCircle.setY(getY() + 10);
            if (Intersector.overlaps(boundCircle, boundStone)) return true;
        }

        if (gravity == -1) {
            boundRectangleFly.setY(getY());
            if (Intersector.overlaps(boundRectangleFly, boundStone)) return true;
        }

        if (gravity == -110) {
            boundRectangleDown.setY(getY());
            if (Intersector.overlaps(boundRectangleDown, boundStone)) return true;
        }

        return false;
    }

}
