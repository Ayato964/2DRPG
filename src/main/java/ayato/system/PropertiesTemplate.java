package ayato.system;

import org.ayato.animation.PropertiesComponent;
import org.ayato.animation.TextProperties;
import org.ayato.animation.text.properties.PropertyAction;

import java.awt.*;
import java.util.function.Supplier;

public class PropertiesTemplate {
    public static TextProperties conv(PropertyAction action, Supplier<String>... strings){
        return PropertiesComponent.ofText(50, 80)
                .font(new Font("", Font.PLAIN, 32))
                .color(Color.WHITE)
                .talk(null, true, action, strings)
                .frame(50, 75, 120, 35, ()->Color.WHITE, Color.BLACK);
    }
}
