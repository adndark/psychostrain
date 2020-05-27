
//Physics.java

package PsychoSystem;

import PsychoGame.gameobject.AnimatedObject;
import PsychoGame.*;

//  PsySoft Team 2008
//       (Manuel Espinoza, Alberto Zorrilla, Guillermo Leon y Arquimides Diaz)

public class Physics {
    public static void gravity(AnimatedObject ao){
        double dy = ao.getVY();
        ao.moveYposition(dy);
        ao.setVY(ao.getVY() + 1.123456);
    }
}
