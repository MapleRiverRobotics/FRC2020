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

public class IndexingBelts extends SubsystemBase {
  /**
   * Creates a new IndexingBelts.
   */
  WPI_VictorSPX indexingBeltMotor = new WPI_VictorSPX(RobotMap.indexingBeltMotor);

  public IndexingBelts() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void Forward() {
    indexingBeltMotor.set(1);
  }

  public void Backward() {
    indexingBeltMotor.set(-0.5);
  }

  public void Stop() {
    indexingBeltMotor.set(0);
  }
}
