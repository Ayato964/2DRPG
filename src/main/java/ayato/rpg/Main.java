package ayato.rpg;


import ayato.scene.Title;
import org.ayato.system.LangLoader;
import org.ayato.system.LunchScene;

public class Main {
    public static final String VERSION = "Alpha.1.1";
    public static final LunchScene scene = new LunchScene("Hello");
    public Main(){
    }
    public static void main(String[] args) {
        LangLoader.create("assets/ayato/lang", LangLoader.JAPANESE);
        scene.changeScene(new Title());
        scene.setVisible(true);
    }
}