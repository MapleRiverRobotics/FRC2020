/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.IndexingBeltsForward;
import frc.robot.commands.IntakeIn;
//import frc.robot.commands.IntakeOut;
import frc.robot.commands.LiftingBeltsDown;
import frc.robot.commands.LiftingBeltsUp;
import frc.robot.commands.ShooterForward;
import frc.robot.commands.cascadeHookDown;
import frc.robot.commands.cascadeHookUp;

/**
 * Add your docs here.
 */
public class OI {

    public Joystick joystickDrive = new Joystick(0);
    public JoystickButton shooterForwardButton;
    public JoystickButton indexingBeltsForwardButton;
    public JoystickButton liftingBeltsUpButton;
    public JoystickButton liftingBeltsDownButton;
    public JoystickButton cascadeHookUpButton;
    public JoystickButton cascadeHookDownButton;

    public Joystick joystickOperator = new Joystick(1);
    public JoystickButton intakeInButton;
    public JoystickButton intakeOutButton;

    public OI() {
        if (joystickDrive != null) {
            shooterForwardButton = new JoystickButton(joystickDrive, 1);
            shooterForwardButton.whileHeld(new ShooterForward());
            indexingBeltsForwardButton = new JoystickButton(joystickDrive, 5);
            indexingBeltsForwardButton.whileHeld(new IndexingBeltsForward());
            liftingBeltsUpButton = new JoystickButton(joystickDrive, 5);
            liftingBeltsUpButton.whileHeld(new LiftingBeltsUp());
            liftingBeltsDownButton = new JoystickButton(joystickDrive, 3);
            liftingBeltsDownButton.whileHeld(new LiftingBeltsDown());
            cascadeHookUpButton = new JoystickButton(joystickDrive, 6);
            cascadeHookUpButton.whileHeld(new cascadeHookUp());
            cascadeHookDownButton = new JoystickButton(joystickDrive, 4);
            cascadeHookDownButton.whileHeld(new cascadeHookDown());
        }

        if (joystickOperator != null) {
            intakeInButton = new JoystickButton(joystickOperator, 4); //Y button
            intakeInButton.whileHeld(new IntakeIn());
            //intakeOutButton = new JoystickButton(joystickOperator, 1);//X button
            //intakeOutButton.whileHeld(new IntakeOut());
        }
    }

    public Joystick getJoystickDrive() {
        return joystickDrive;
    }

    public Joystick getJoystickOperator() {
        return joystickOperator;
    }

    public double getJoystickDriveForwardSpeed() {
        if (joystickDrive != null) {
            return joystickDrive.getY() * 1;
        }
        // if the drive joystick is not plugged in, use the operator joystick (gamepad)
        // to drive
        return joystickOperator.getY();
    }

    public double getJoystickDriveThrottleSpeed() {
        if (joystickDrive != null) {
            return (joystickDrive.getThrottle() * -1.0 + 1.0) / 2.0;
        }
        return .7;
    }

    public double getJoystickDriveRotation() {
        if (joystickDrive != null) {
            return joystickDrive.getZ() * -0.9;
        }
        // if the drive joystick is not plugged in, use the operator joystick (gamepad)
        // to drive
        return joystickOperator.getX() * .9;
    }
}
