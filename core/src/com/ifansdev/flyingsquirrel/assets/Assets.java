package com.ifansdev.flyingsquirrel.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
    private final AssetManager assetManager = new AssetManager();

    public static final String BG = "bg";
    public static final String FOREST = "forest";
    public static final String GROUND = "ground";
    public static final String SQUIRREL_SPIN = "squirrelSpin";
    public static final String SQUIRREL_DOWN = "squirrelDown";
    public static final String STONE = "stone";
    public static final String FLOWER = "flower";

    public static final String SQUIRREL_TAIL_UP = "squirrelTailUp";
    public static final String SQUIRREL_TAIL_MEDIUM = "squirrelTailMedium";
    public static final String SQUIRREL_TAIL_DOWN = "squirrelTailDown";
    private TextureRegion[] squirrelFly;

    private TextureAtlas atlas;

    private Skin skin;

    public Assets() {
        skin = new Skin();
        generatorOfFonts();
        load();
        done();
    }

    private void generatorOfFonts() {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("data/font/Bittypix Monospace.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.color = Color.RED;
        fontParameter.size = 28;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.borderWidth = 1.2f;
        fontParameter.minFilter = Texture.TextureFilter.Linear;
        fontParameter.magFilter = Texture.TextureFilter.Linear;

        BitmapFont font = fontGenerator.generateFont(fontParameter);
        font.getData().setScale(0.5f);

        skin.add("font", font, BitmapFont.class);

        fontGenerator.dispose();
    }

    private void load() {
            assetManager.load("data/texture/texture.atlas", TextureAtlas.class);

            assetManager.finishLoading();

        }

    private void done() {

        atlas = assetManager.get("data/texture/texture.atlas", TextureAtlas.class);

        skin.addRegions(atlas);

        skin.load(Gdx.files.internal("data/skin/skin.json"));

        squirrelFly = new TextureRegion[]{
                getTexture(SQUIRREL_TAIL_UP),
                getTexture(SQUIRREL_TAIL_MEDIUM),
                getTexture(SQUIRREL_TAIL_DOWN)
        };
    }

    public TextureRegion getTexture(String name) {
        return atlas.findRegion(name);
    }

    public Skin getSkin() {
        return skin;
    }

    public TextureRegion[] getSquirrelFly() {
        return squirrelFly;
    }

    public void dispose() {
        assetManager.dispose();
    }

}
