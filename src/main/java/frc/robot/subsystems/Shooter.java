/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  private final WPI_TalonSRX shooterController4;
  private final WPI_VictorSPX shooterController3;
  private final WPI_VictorSPX shooterController2;
  private final WPI_VictorSPX shooterController1;

  public Shooter() {
    shooterController4 = new WPI_TalonSRX(RobotMap.shooterMotor4);
    shooterController4.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    shooterController2 = new WPI_VictorSPX(RobotMap.shooterMotor2);
    shooterController3 = new WPI_VictorSPX(RobotMap.shooterMotor3);
    shooterController1 = new WPI_VictorSPX(RobotMap.shooterMotor1);

    shooterController1.setNeutralMode(NeutralMode.Coast);
    shooterController2.setNeutralMode(NeutralMode.Coast);
    shooterController3.setNeutralMode(NeutralMode.Coast);
    shooterController4.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // public double speed = 0.8;
  // public double speedBackward = 0.2;

  public void setShooterSpeed(double shooterDesiredSpeed) {

    double shooterSpeed = 0;

    double velocity = this.shooterController4.getSelectedSensorVelocity();
    double rpm = velocity / 4096 * 10 * 60;
    SmartDashboard.putNumber("RPM", rpm);

    if (rpm < shooterDesiredSpeed) {
      shooterSpeed = 1;
    }else if(rpm > shooterDesiredSpeed){
      shooterSpeed = 0;
    }

    shooterController1.set(shooterSpeed * -1);
    shooterController2.set(shooterSpeed);
    shooterController3.set(shooterSpeed * -1);
    shooterController4.set(shooterSpeed * -1);
  }

  public void Stop(){
    shooterController1.set(0);
    shooterController2.set(0);
    shooterController3.set(0);
    shooterController4.set(0);
  }

}
