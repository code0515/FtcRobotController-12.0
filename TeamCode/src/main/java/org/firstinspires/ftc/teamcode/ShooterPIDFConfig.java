
package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;

/**
 * Values that students can change live in FTC Dashboard.
 *
 * These are starting values ONLY, not tuned coefficients.
 *
 * Units for TARGET_VELOCITY: encoder ticks per second.
 */
@Config
public class ShooterPIDFConfig {

    public static double P = 0;
    public static double I = 0;
    public static double D = 0;
    public static double F = 0;

    // Existing shooter target from TeleOp.
    public static double TARGET_VELOCITY = 1500;

    // Acceptable velocity error for testing.
    public static double VELOCITY_TOLERANCE = 50;
}
