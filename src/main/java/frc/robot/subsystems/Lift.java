/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Lift extends SubsystemBase {
  /**
   * Creates a new Lift.
   */
  public static CANSparkMax liftMotor = new CANSparkMax(RobotMap.LiftMotor, MotorType.kBrushless);
  
  public Lift() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void Up(){
    liftMotor.set(0.8);
  }

  public void Down(){
    liftMotor.set(-0.8);

  }

  public void Stop(){
    liftMotor.set(0);
  }
}
