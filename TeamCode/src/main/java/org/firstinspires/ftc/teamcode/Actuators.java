// This template is a starting point on setting up your actuators
// If you are not using anything listed below, comment them out (like this line)
// Deleting them is fine too but commenting is recommended in case they are added later

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Actuators {

    // TODO: If using motors, add/edit them here (up to 8)
    public DcMotor leftFrontDrive;
    public DcMotor rightFrontDrive;
    public DcMotor leftBackDrive;
    public DcMotor rightBackDrive;
    public DcMotor intake;
    public DcMotorEx activeShooter;
    public DcMotor turret;

    // TODO: If using servos or continuous servos, add them here (up to 12)
    public Servo gateServo;
    //public CRServo servoContinuous;

    public void init(HardwareMap hwMap) {
        // TODO: Put motor hardware here, don't forget to rename deviceNames to match yours
        leftFrontDrive = hwMap.get(DcMotor.class, "leftFrontDrive");
        rightFrontDrive = hwMap.get(DcMotor.class, "rightFrontDrive");
        leftBackDrive = hwMap.get(DcMotor.class, "leftBackDrive");
        rightBackDrive = hwMap.get(DcMotor.class, "rightBackDrive");
        intake = hwMap.get(DcMotor.class, "intake");
        activeShooter = hwMap.get(DcMotorEx.class, "activeShooter");
        turret = hwMap.get(DcMotor.class, "turret");

        //TODO: Setup motor mode when power is Zero
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //or FLOAT
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //or FLOAT
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //or FLOAT
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //or FLOAT
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //or FLOAT
        activeShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT); //or FLOAT
        turret.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //or BRAKE
        //TODO: If need to change motor direction
        leftFrontDrive.setDirection(DcMotorSimple.Direction.REVERSE); //or FORWARD
        leftBackDrive.setDirection(DcMotorSimple.Direction.REVERSE); //or FORWARD
        intake.setDirection(DcMotorSimple.Direction.REVERSE); //or FORWARD

        //TODO: Put servo hardware here, don't forget to rename deviceNames to match yours
        gateServo = hwMap.get(Servo.class, "gateServo");
        //servoContinuous = hwMap.get(CRServo.class, "servoContinuous");
        //TODO: If need to change servo direction
        gateServo.setDirection(Servo.Direction.FORWARD); //or REVERSE
        //servoContinuous.setDirection(CRServo.Direction.FORWARD); //or REVERSE
        //TODO: If need to set the logical min and max of a servo.
        //gateServo.scaleRange(0.4, 0.6);  //Change the 0 and 1 if needed
    }

    //TODO: Sample classes for turning on motors using power only (Useful for TeleOp)
    public void leftFront(double power) {
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFrontDrive.setPower(power);
    }
    public void rightFront(double power) {
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setPower(power);
    }
    public void leftBack(double power) {
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setPower(power);
    }
    public void rightBack(double power) {
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setPower(power);
    }

    public void intake(double power) {
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setPower(power);
    }

    public void activeShooter(double velocity) {
        activeShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        activeShooter.setVelocity(velocity);
    }


    //TODO: Sample classes for powering a servo
    public void gateServo(double position) {
        gateServo.setDirection(Servo.Direction.REVERSE);
        gateServo.setPosition(position);
    }

}
