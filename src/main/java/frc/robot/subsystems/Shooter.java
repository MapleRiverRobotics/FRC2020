/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
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

  private static final int kSlotIdx = 0;
  private static final int kPIDLoopIdx = 0;
  private static final int kTimeoutMs = 30;
  private static final double kP = 0.125;
  private static final double kI = 0.001;
  private static final double kD = 2;
  private static final double kF = .07; // 1023.0/7200.0;
  private static final int kIzone = 300;
  private static final double kPeakOutput = 1.00;

  public double desiredSpeed;
  public double rpm;

  public Shooter() {
    shooterController4 = new WPI_TalonSRX(RobotMap.shooterMotor4);
    shooterController2 = new WPI_VictorSPX(RobotMap.shooterMotor2);
    shooterController3 = new WPI_VictorSPX(RobotMap.shooterMotor3);
    shooterController1 = new WPI_VictorSPX(RobotMap.shooterMotor1);

    shooterController4.setNeutralMode(NeutralMode.Coast);
    shooterController4.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
    shooterController4.setSensorPhase(true);
    shooterController4.configNominalOutputForward(0, kTimeoutMs);
    shooterController4.configNominalOutputReverse(0, kTimeoutMs);
    shooterController4.configPeakOutputForward(1, kTimeoutMs);
    shooterController4.configPeakOutputReverse(-1, kTimeoutMs);

    shooterController4.config_kF(kSlotIdx, kF, kTimeoutMs);
    shooterController4.config_kP(kSlotIdx, kP, kTimeoutMs);
    shooterController4.config_kI(kSlotIdx, kI, kTimeoutMs);
    shooterController4.config_kD(kSlotIdx, kD, kTimeoutMs);

    shooterController4.setInverted(true);
    shooterController1.follow(shooterController4);
    shooterController1.setInverted(true);
    shooterController2.follow(shooterController4);
    shooterController2.setInverted(false);
    shooterController3.follow(shooterController4);
    shooterController3.setInverted(true);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // public double speed = 0.8;
  // public double speedBackward = 0.2;

  public void setShooterSpeed(double shooterDesiredSpeed) {

    // double shooterSpeed = 0;

    shooterController4.set(ControlMode.Velocity, shooterDesiredSpeed * 4096 / 600);

    double velocity = this.shooterController4.getSelectedSensorVelocity();
    rpm = velocity / 4096 * 10 * 60;
    desiredSpeed = shooterDesiredSpeed;
    SmartDashboard.putNumber("RPM", rpm);

    // if (rpm < shooterDesiredSpeed) {
    // shooterSpeed = 1;
    // }else if(rpm > shooterDesiredSpeed){
    // shooterSpeed = 0;
    // }

    // shooterController4.set(shooterSpeed * -1);
  }

  public void Stop() {
    shooterController4.stopMotor();
  }

  public boolean isWheelUpToSpeed(){
    if(rpm > desiredSpeed - 50){
      if(rpm < desiredSpeed + 50){
        return true;
      }
    }else if(rpm > desiredSpeed + 50 || rpm < desiredSpeed - 50){
      return false;
    }
    return false;
  }

}

