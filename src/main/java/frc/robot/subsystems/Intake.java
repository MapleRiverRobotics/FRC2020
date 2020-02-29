/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  WPI_VictorSPX intakeMotor = new WPI_VictorSPX(RobotMap.intakeMotor);
  WPI_VictorSPX intakeLiftMotor = new WPI_VictorSPX(RobotMap.intakeLiftMotor);

  public Intake() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void In() {
    intakeMotor.set(0.8);
  }

  public void Out() {
    intakeMotor.set(-0.8);
  }

  public void Stop() {
    intakeMotor.set(0);
  }

  public void IntakePosition(double intakeLiftSpeed){
    intakeLiftMotor.set(intakeLiftSpeed);
  }
}
