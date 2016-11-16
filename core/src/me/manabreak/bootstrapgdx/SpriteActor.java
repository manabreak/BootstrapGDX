package me.manabreak.bootstrapgdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SpriteActor extends Actor {

    public Sprite sprite;
    public float offsetX = 0f;
    public float offsetY = 0f;
    private boolean drawEnabled = true;

    public SpriteActor() {

    }

    public SpriteActor(Sprite sprite) {
        setSprite(sprite);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        if (sprite != null) {
            setSize(sprite.getWidth(), sprite.getHeight());
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (drawEnabled && sprite != null) {
            sprite.draw(batch, parentAlpha);
        }
        super.draw(batch, parentAlpha);
    }

    @Override
    public void setPosition(float x, float y, int alignment) {
        sprite.setPosition(x + offsetX, y + offsetY);
        super.setPosition(x, y, alignment);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        sprite.setPosition(x + offsetX, y + offsetY);
    }

    @Override
    public float getWidth() {
        return sprite.getWidth();
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        sprite.setSize(width, sprite.getHeight());
    }

    @Override
    public float getHeight() {
        return sprite.getHeight();
    }

    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        sprite.setScale(sprite.getWidth(), height);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        sprite.setX(x + offsetX);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        sprite.setY(y + offsetY);
    }

    @Override
    public void moveBy(float x, float y) {
        super.moveBy(x, y);
        sprite.setPosition(getX() + offsetX, getY() + offsetY);
    }

    @Override
    public void setScale(float scaleX, float scaleY) {
        super.setScale(scaleX, scaleY);
        sprite.setScale(scaleX, scaleY);
    }

    @Override
    public void setScaleX(float scaleX) {
        super.setScaleX(scaleX);
        sprite.setScale(scaleX, sprite.getScaleY());
    }

    @Override
    public void setScaleY(float scaleY) {
        super.setScaleY(scaleY);
        sprite.setScale(sprite.getScaleX(), scaleY);
    }

    @Override
    public void setScale(float scaleXY) {
        super.setScale(scaleXY);
        sprite.setScale(scaleXY);
    }

    @Override
    public void setRotation(float degrees) {
        super.setRotation(degrees);
        sprite.setRotation(degrees);
    }

    @Override
    public void setOrigin(float originX, float originY) {
        super.setOrigin(originX, originY);
        sprite.setOrigin(originX, originY);
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        sprite.setColor(color);
    }

    @Override
    public void setColor(float r, float g, float b, float a) {
        super.setColor(r, g, b, a);
        sprite.setColor(r, g, b, a);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        sprite.setSize(width, height);
    }

    public void setDrawEnabled(boolean drawEnabled) {
        this.drawEnabled = drawEnabled;
    }
}
