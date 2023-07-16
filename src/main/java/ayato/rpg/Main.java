package ayato.rpg;


import ayato.scene.Title;
import org.ayato.system.LangLoader;
import org.ayato.system.LunchScene;

public class Main {
    public static final LunchScene scene = new LunchScene("Hello");
    public Main(){
    }
    public static void main(String[] args) {
        LangLoader.create("assets/ayato/lang", LangLoader.JAPANESE);
        Main e = new Main();
        scene.changeScene(new Title());
        scene.setVisible(true);
    }
}