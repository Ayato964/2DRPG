package ayato.rpg;


import org.ayato.system.ExecuteScene;
import org.ayato.util.IBaseScene;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        ExecuteScene scene = new ExecuteScene("Hello");
        scene.changeScene(new IBaseScene() {
            @Override
            public void display(Graphics graphics) {

            }

            @Override
            public void setup(ExecuteScene executeScene) {

            }
        });
        scene.setVisible(true);
    }
}