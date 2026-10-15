
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/*
 * This OpMode executes a Tank Drive control TeleOp a direct drive robot
 * The code is structured as an Iterative OpMode
 *
 * In this mode, the left and right joysticks control the left and right motors respectively.
 * Pushing a joystick forward will make the attached motor drive forward.
 * It raises and lowers the claw using the Gamepad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@TeleOp(name="Robot: Teleop Tank", group="Robot")

public class RobotTeleopTank_Iterative extends OpMode{

    /* Declare OpMode members. */
    public DcMotor  leftFrontDrive;
    public DcMotor  rightFrontDrive;
    public DcMotor  leftBackDrive;
    public DcMotor  rightBackDrive;
    public DcMotor  intake;
    public DcMotor  turret;




    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        // Define and Initialize Motors
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "leftBackDrive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBackDrive");
        intake = hardwareMap.get(DcMotor.class, "intake");
        turret = hardwareMap.get(DcMotor.class, "activeShooter");

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left and right sticks forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);



        // If there are encoders connected, switch to RUN_USING_ENCODER mode for greater accuracy
        // leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Define and initialize ALL installed servos.
//        leftClaw  = hardwareMap.get(Servo.class, "left_hand");
//        rightClaw = hardwareMap.get(Servo.class, "right_hand");
//        leftClaw.setPosition(MID_SERVO);
//        rightClaw.setPosition(MID_SERVO);

        // Send telemetry message to signify robot waiting;
        telemetry.addData(">", "Robot Ready.  Press START.");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit START
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits START
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */
    @Override
    public void loop() {
        double frontLeft;
        double backStrafeLeft;
        double frontRight;
        double frontStrafeLeft;
        double frontStrafeRight;
        double backStrafeRight;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forward, so negate it)
        frontLeft = -gamepad1.left_stick_y;
        frontRight = -gamepad1.left_stick_y;

        leftFrontDrive.setPower(frontLeft);
        rightFrontDrive.setPower(frontRight);

        backStrafeRight = gamepad1.left_stick_x;
        backStrafeLeft = -gamepad1.left_stick_x;
        frontStrafeLeft = gamepad1.left_stick_x;
        frontStrafeRight = -gamepad1.left_stick_x;

        leftFrontDrive.setPower(frontStrafeLeft);
        leftBackDrive.setPower(backStrafeLeft);
        rightFrontDrive.setPower(frontStrafeRight);
        rightBackDrive.setPower(backStrafeRight);




        // Use gamepad left & right Bumpers to open and close the claw


        // Move both servos to new position.  Assume servos are mirror image of each other.


        // Use gamepad buttons to move the arm up (Y) and down (A)
        if (gamepad1.y)
            intake.setPower(0);
        else if (gamepad1.a)
            intake.setPower(1);
        else
            intake.setPower(0.0);

        // Send telemetry message to signify robot running;

        telemetry.addData("left",  "%.2f", frontLeft);
        telemetry.addData("right", "%.2f", frontRight);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
