package ayato.system;

import org.ayato.util.VoidSupplier;

public class Debug {
    public static final boolean MODE = true;

    public static void method(VoidSupplier action){
        if(MODE)
            action.action();
    }
}
