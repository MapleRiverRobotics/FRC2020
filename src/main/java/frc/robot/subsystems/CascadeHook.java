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

public class CascadeHook extends SubsystemBase {
  /**
   * Creates a new CascadeHook.
   */
  WPI_VictorSPX cascadeHookMotor = new WPI_VictorSPX(RobotMap.CascadeHookMotor);

  public CascadeHook() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void Up() {
    cascadeHookMotor.set(0.8);
  }

  public void Down() {
    cascadeHookMotor.set(-0.8);
  }

  public void Stop(){
    cascadeHookMotor.set(0);
  }
}
