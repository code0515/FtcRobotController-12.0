package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "TeleOperated")
public class TeleOperated extends LinearOpMode {
    //    boolean intakeInKey = gamepad2.a;
//    boolean intakeOutKey = gamepad2.b;

//    boolean gateServoOpen = gamepad2.left_bumper;
//    boolean resetHeading = gamepad1.start;
//    boolean shooter = gamepad2.y;

    double reset_angle = 0;
    Actuators actuator = new Actuators();
    Sensors sensor = new Sensors();
    boolean cd = true;
    double turretPosition;

    @Override
    public void runOpMode() {
        actuator.init(hardwareMap);
        sensor.init(hardwareMap);

        actuator.turret.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        actuator.turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                //do things here
                drive();
                resetAngle();
                intake();
                activeShooter();
                gateServo();
//                turret();
                telemetry.update();
            }
        }
    }

    private void gateServo() {
        //reversed at 0.22 is good for blocking the exit
            if (gamepad2.left_bumper) {
                actuator.gateServo(0.19);
            } else {
                actuator.gateServo(0);
            }
    }

    public void drive() {
        double Protate = - gamepad1.right_stick_x;
        double stick_x = - gamepad1.left_stick_x * Math.sqrt(Math.pow(1-Math.abs(Protate), 2) );
        //Accounts for Protate when limiting magnitude to be less than 1
        double stick_y = - gamepad1.left_stick_y * Math.sqrt(Math.pow(1-Math.abs(Protate), 2) );
        double theta = 0;
        double Px = 0;
        double Py = 0;

        double gyroAngle = getHeading() * Math.PI / 180; //Converts gyroAxngle into radians
        if (gyroAngle <= 0) {
            gyroAngle = gyroAngle + (Math.PI / 2);
        } else if (0 < gyroAngle && gyroAngle < Math.PI / 2) {
            gyroAngle = gyroAngle + (Math.PI / 2);
        } else if (Math.PI / 2 <= gyroAngle) {
            gyroAngle = gyroAngle - (3 * Math.PI / 2);
        }
        gyroAngle = -1 * gyroAngle;

        //Linear directions in case you want to do straight lines.
//        if(gamepad1.dpad_right){
//            stick_x = 0.5;
//        }
//        else if(gamepad1.dpad_left){
//            stick_x = -0.5;
//        }
        if(gamepad1.dpad_up){
            stick_y = -0.5;
        }
        else if(gamepad1.dpad_down){
            stick_y = 0.5;
        }

        //MOVEMENT
        theta = Math.atan2(stick_y, stick_x) - gyroAngle - (Math.PI / 2);
        Px = Math.sqrt(Math.pow(stick_x, 2) + Math.pow(stick_y, 2)) * (Math.sin(theta + Math.PI / 4));
        Py = Math.sqrt(Math.pow(stick_x, 2) + Math.pow(stick_y, 2)) * (Math.sin(theta - Math.PI / 4));

        telemetry.addData("Stick_X", stick_x);
        telemetry.addData("Stick_Y", stick_y);
        telemetry.addData("Magnitude",  Math.sqrt(Math.pow(stick_x, 2) + Math.pow(stick_y, 2)));
        telemetry.addData("Motor Front Left", Py - Protate);
        telemetry.addData("Motor Front Right", Px + Protate);
        telemetry.addData("Motor Back Left", Px - Protate);
        telemetry.addData("Motor Back Right", Py + Protate);

        actuator.leftFront((Py - Protate));
        actuator.leftBack((Px - Protate));
        actuator.rightBack((Py + Protate));
        actuator.rightFront((Px + Protate));
    }

    public void resetAngle(){
        //press start to reset robot heading
        if(gamepad1.start){
            reset_angle = getHeading() + reset_angle;
        }
    }

    public double getHeading(){

        double heading;
        //Get heading from the IMU or the navX
        heading = sensor.getHeading(AngleUnit.DEGREES); //Detecting yaw value from imu

        if(heading < -180) {
            heading = heading + 360;
        }

        else if(heading > 180) {
            heading = heading - 360;
        }

        heading = heading - reset_angle;
        telemetry.addData("Heading (Degrees)", heading);
        telemetry.addData("Heading (Radians)", heading * 3.1415926535 / 90);
        return heading;
    }

    public void intake() {
        if (gamepad2.a) {
            actuator.intake(1);
        }
        else if (gamepad2.b) {
            actuator.intake(-0.5);
        }
        else {
            actuator.intake(0);
        }
    }
    public void activeShooter() {

        if (gamepad2.y) {
            actuator.activeShooter(1500);  //You may need to change this
        } else {
            actuator.activeShooter(0);
        }
        telemetry.addData("Shooter Velocity", (actuator.activeShooter.getVelocity()));
    }
//    public void turret() {
//        turretPosition = Math.min(Math.max(turretPosition, -214), 214);
//        if (gamepad1.dpad_right) {
//            turretPosition = turretPosition + 1;
//            actuator.turret.setTargetPosition((int) turretPosition);
//            actuator.turret.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        } else if (gamepad1.dpad_left) {
//            turretPosition = turretPosition - 1;
//            actuator.turret.setTargetPosition((int) turretPosition);
//            actuator.turret.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        }
//
//        actuator.turret.setPower(1);
//        telemetry.addData("turret position",turretPosition);
//        telemetry.update();
//    }

}
