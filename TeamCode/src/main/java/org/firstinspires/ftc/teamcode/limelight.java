//package org.firstinspires.ftc.teamcode;
//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
//
//import com.qualcomm.hardware.limelightvision.LLResult;
//import com.qualcomm.hardware.limelightvision.LLResultTypes;
//import com.qualcomm.hardware.limelightvision.LLStatus;
//import com.qualcomm.hardware.limelightvision.Limelight3A;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
//
//public class Teleop extends LinearOpMode {
//
//    private Limelight3 A limelight;
//    Limelight3A limelight;
//
//    @Override
//    public void init() {
//        limelight = hardwareMap.get(Limelight3A.class, "limelight");
//        limelight.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)
//        limelight.start(); // This tells Limelight to start looking!
//    }
//    @Override
//    public void runOpMode() throws InterruptedException
//    {
//        limelight = hardwareMap.get(Limelight3A.class, "limelight");
//
//        telemetry.setMsTransmissionInterval(11);
//
//        limelight.pipelineSwitch(0);
//
//        /*
//         * Starts polling for data.
//         */
//        limelight.start();
//
//        while (opModeIsActive()) {
//            LLResult result = limelight.getLatestResult();
//            if (result != null) {
//                if (result.isValid()) {
//                    Pose3D botpose = result.getBotpose();
//                    telemetry.addData("tx", result.getTx());
//                    telemetry.addData("ty", result.getTy());
//                    telemetry.addData("Botpose", botpose.toString());
//                }
//            }
//        }
//    }
//}