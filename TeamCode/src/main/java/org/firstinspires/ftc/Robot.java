package org.firstinspires.ftc;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;


public class Robot {



    ElapsedTime time;
    VerticalSlides verticalSlides;
    HorizontalSlides horizontalSlides;
    Aperture aperture;

    int pos = 0;
    boolean start = false;
    boolean prevStart = false;

    public Robot(Aperture aperture, HorizontalSlides horizontalSlides, VerticalSlides verticalSlides){
        this.aperture = aperture;
        this.horizontalSlides = horizontalSlides;
        this.verticalSlides = verticalSlides;

        time = new ElapsedTime();
    }
    public void transfer(Gamepad manipulator,Gamepad prevManipulator){
        if(manipulator.left_bumper && !prevManipulator.left_bumper){
            pos++;
        }
        if(pos == 0){
            horizontalSlides.setTarget(HorizontalSlides.RETRACTED);
            aperture.flipUp();
            verticalSlides.setTarget(VerticalSlides.BOTTOM);
        }
        if(pos == 1){
            //Extend horizontal slides
            horizontalSlides.setTarget(HorizontalSlides.EXTENDED);
            if(horizontalSlides.getPos() > (int) HorizontalSlides.EXTENDED / 2){
                aperture.flipDown();
            }
        }
        if(pos==2){
            aperture.flipUp();
            if(aperture.getPos() > 0.5){
                horizontalSlides.setTarget(0);
            }
        }
        if(pos == 3){
            //Move sample from aperture to vertical slides
            start = true;
            if(start && !prevStart){
                aperture.eject();
                time.reset();
            }
            if(time.seconds() > 1){
                aperture.stop();
            }
            prevStart=start;
        }
        if(pos==4){
            verticalSlides.setTarget(VerticalSlides.HIGHBASKET);
        }
        if(pos==5){
            //Deposit sample
        }

        pos%=6;
    }
}
