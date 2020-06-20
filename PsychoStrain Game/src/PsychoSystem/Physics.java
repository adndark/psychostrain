package PsychoSystem;

import PsychoGame.gameobject.AnimatedObject;

//  PsySoft Team 2008
//       (Manuel Espinoza, Alberto Zorrilla, Guillermo Leon y Arquimedes Diaz)
public class Physics {

    public static void gravity(AnimatedObject ao) {
        double dy = ao.getVY();
        ao.moveYposition(dy);
        ao.setVY(ao.getVY() + 1.123456);
    }
}
