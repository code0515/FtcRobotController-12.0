package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Actuators;
import org.firstinspires.ftc.teamcode.Sensors;

@Autonomous(name = "Left Launch Park")
public class AutoLeftLaunchPark extends LinearOpMode {

    Actuators actuator = new Actuators();
    Sensors sensor = new Sensors();

    //double turretPosition;

    @Override
    public void runOpMode() {
        actuator.init(hardwareMap);
        sensor.init(hardwareMap);

        //actuator.turret.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //actuator.turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                //do things here
                sleep(10000);
                shooter(1500);
                sleep(2000);

                intake(1);
                sleep(2000);

                shooter(0);
                intake(0);

                driveForward(0.3);
                sleep(500);


                strafeRight(0.6);
                sleep(1500);

                stopMoving();

                break; //stop the code
            }
        }
    }

    public void driveForward(double speed) {
        actuator.leftFront(speed);
        actuator.rightFront(speed);
        actuator.leftBack(speed);
        actuator.rightBack(speed);
    }

    public void driveBackward(double speed) {
        actuator.leftFront(-speed);
        actuator.rightFront(-speed);
        actuator.leftBack(-speed);
        actuator.rightBack(-speed);
    }

    public void turnLeft(double speed) {
        actuator.leftFront(-speed);
        actuator.rightFront(speed);
        actuator.leftBack(-speed);
        actuator.rightBack(speed);
    }

    public void strafeRight(double speed) {
        actuator.leftFront(speed);
        actuator.rightFront(-speed);
        actuator.leftBack(-speed);
        actuator.rightBack(speed);
    }

    public void strafeLeft(double speed) {
        actuator.leftFront(-speed);
        actuator.rightFront(speed);
        actuator.leftBack(speed);
        actuator.rightBack(-speed);
    }

    public void turnRight(double speed) {
        actuator.leftFront(speed);
        actuator.rightFront(-speed);
        actuator.leftBack(speed);
        actuator.rightBack(-speed);
    }

    public void stopMoving() {
        actuator.leftFront(0);
        actuator.rightFront(0);
        actuator.leftBack(0);
        actuator.rightBack(0);
    }

    public void intake(double speed) {
            actuator.intake(speed);
    }
    public void shooter(double speed) {
        actuator.activeShooter(speed);
    }
}
