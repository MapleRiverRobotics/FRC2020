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

public class LiftingBelts extends SubsystemBase {
  /**
   * Creates a new LiftingBelts.
   */

  public static CANSparkMax liftingBeltsMotor = new CANSparkMax(RobotMap.liftingBeltsMotor, MotorType.kBrushless);

  public LiftingBelts() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void Up() {
    liftingBeltsMotor.set(-0.8);
  }

  public void Down() {
    liftingBeltsMotor.set(0.2);
  }

  public void Stop(){
    liftingBeltsMotor.set(0);
  }
}
