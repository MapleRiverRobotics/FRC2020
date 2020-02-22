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

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  WPI_VictorSPX shooterController1 = new WPI_VictorSPX(RobotMap.shooterMotor1);
  WPI_VictorSPX shooterController2 = new WPI_VictorSPX(RobotMap.shooterMotor2);
  WPI_VictorSPX shooterController3 = new WPI_VictorSPX(RobotMap.shooterMotor3);
  WPI_VictorSPX shooterController4 = new WPI_VictorSPX(RobotMap.shooterMotor4);

  public Shooter() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  
  //public double speed = 0.8;
  //public double speedBackward = 0.2;

  public void setShooterSpeed(double shooterSpeed){
    shooterController1.set(shooterSpeed * -1);
    shooterController2.set(shooterSpeed);
    shooterController3.set(shooterSpeed * -1);
    shooterController4.set(shooterSpeed * -1);
  }


public class setShooterSpeed {
}

  // public void Forward() {
  //   // shooterController1.set(-0.8);
  //   // shooterController2.set(0.8);
  //   // shooterController3.set(-0.8);
  //   // shooterController4.set(-0.8);
  //   shooterController1.set(speed * -1);
  //   shooterController2.set(speed);
  //   shooterController3.set(speed * -1);
  //   shooterController4.set(speed * -1);

  // }

  // public void Backward() {
  //   shooterController1.set(speedBackward * -1);
  //   shooterController2.set(speedBackward);
  //   shooterController3.set(speedBackward * -1);
  //   shooterController4.set(speedBackward * -1);

  // }

  // public void Stop() {
  //   shooterController1.set(0);
  //   shooterController2.set(0);
  //   shooterController3.set(0);
  //   shooterController4.set(0);
  // }
}
