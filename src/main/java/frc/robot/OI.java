/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AimAndShoot;
import frc.robot.commands.IndexingBeltsForward;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakePositionCommand;
import frc.robot.commands.LiftDown;
import frc.robot.commands.LiftUp;
import frc.robot.commands.ShooterSpeedCommand;
import frc.robot.commands.cascadeHookDown;
import frc.robot.commands.cascadeHookUp;
import frc.robot.commands.liftingBeltsCommand;

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
    public JoystickButton liftUpButton;
    public JoystickButton liftDownButton;
    public JoystickButton shooter100Button;
    public JoystickButton shooter95Button;
    public JoystickButton shooter90Button;
    public JoystickButton shooter85Button;
    public JoystickButton shooter80Button;
    public JoystickButton aimAndShootButton;
    public JoystickButton shooter70Button;
    public JoystickButton intakeIn;

    public Joystick joystickOperator = new Joystick(1);
    public JoystickButton intakeInButton;
    public JoystickButton intakeOutButton;
    public JoystickButton liftingBeltsUpOperatorButton;
    public JoystickButton liftingBeltsDownOperatorButton;
    public JoystickButton intakeLiftUpButton;
    public JoystickButton intakeLiftDownButton;

    public OI() {
        if (joystickDrive != null) {
            // shooterForwardButton = new JoystickButton(joystickDrive, 1);
            // shooterForwardButton.whileHeld(new ShooterForward());
            shooter100Button = new JoystickButton(joystickDrive, 12);
            shooter100Button.whileHeld(new ShooterSpeedCommand(4500));
            shooter95Button = new JoystickButton(joystickDrive, 11);
            shooter95Button.whileHeld(new ShooterSpeedCommand(4250));
            shooter90Button = new JoystickButton(joystickDrive, 10);
            shooter90Button.whileHeld(new ShooterSpeedCommand(4000));
            shooter85Button = new JoystickButton(joystickDrive, 9);
            shooter85Button.whileHeld(new ShooterSpeedCommand(3750));
            //shooter80Button = new JoystickButton(joystickDrive, 8);
            //shooter80Button.whileHeld(new ShooterSpeedCommand(0.8));
            shooter70Button = new JoystickButton(joystickDrive, 1);
            shooter70Button.whileHeld(new ShooterSpeedCommand(3500));

            aimAndShootButton = new JoystickButton(joystickDrive, 7);
            aimAndShootButton.whileHeld(new AimAndShoot());
            //indexingBeltsForwardButton = new JoystickButton(joystickDrive, 2);
            //indexingBeltsForwardButton.whileHeld(new IndexingBeltsForward());
            liftingBeltsUpButton = new JoystickButton(joystickDrive, 2);
            liftingBeltsUpButton.whileHeld(new liftingBeltsCommand(-0.8));
            liftingBeltsDownButton = new JoystickButton(joystickDrive, 3);
            liftingBeltsDownButton.whileHeld(new liftingBeltsCommand(0.2));
            //cascadeHookUpButton = new JoystickButton(joystickDrive, 5);
            //cascadeHookUpButton.whileHeld(new cascadeHookUp());
            //cascadeHookDownButton = new JoystickButton(joystickDrive, 3);
            //cascadeHookDownButton.whileHeld(new cascadeHookDown());
            liftUpButton = new JoystickButton(joystickDrive, 6);
            liftUpButton.whileHeld(new LiftUp());
            liftDownButton = new JoystickButton(joystickDrive, 4);
            liftDownButton.whileHeld(new LiftDown());

            intakeIn = new JoystickButton(joystickDrive, 8);
            intakeIn.whileHeld(new IntakeIn());

        }

        if (joystickOperator != null) {
            intakeInButton = new JoystickButton(joystickOperator, 6); // RB button
            intakeInButton.whileHeld(new IntakeIn());
            liftingBeltsUpOperatorButton = new JoystickButton(joystickOperator, 2); // B Joystick
            liftingBeltsUpOperatorButton.whileHeld(new liftingBeltsCommand(-0.2));
            liftingBeltsDownOperatorButton = new JoystickButton(joystickOperator, 3); // X Joystick
            liftingBeltsDownOperatorButton.whileHeld(new liftingBeltsCommand(0.2));
            intakeLiftUpButton = new JoystickButton(joystickOperator, 4); // Y button
            intakeLiftUpButton.whileHeld(new IntakePositionCommand(1));
            intakeLiftDownButton = new JoystickButton(joystickOperator, 1); // A button
            intakeLiftDownButton.whileHeld(new IntakePositionCommand(-0.8));
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
