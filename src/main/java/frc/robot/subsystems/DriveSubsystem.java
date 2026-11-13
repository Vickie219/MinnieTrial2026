package frc.robot.subsystems;
import frc.robot.Constants;
import frc.robot.RobotContainer;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;


import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


//drive train  motors 
public class DriveSubsystem extends SubsystemBase {

    // private final Constants constants = new Constants();

    private DifferentialDrive differentialDrive;
    private final CommandXboxController xboxController = new CommandXboxController(0);
    private final SparkMax leftfollower = new SparkMax(Constants.leftFollowerID, MotorType.kBrushless);
    private final SparkMax leftleader = new SparkMax(Constants.leftLeaderID, MotorType.kBrushless);
    private final SparkMax rightfollower = new SparkMax(Constants.rightFollowerID, MotorType.kBrushless);
    private final SparkMax rightleader = new SparkMax(Constants.rightLeaderID, MotorType.kBrushless);

    //private final DifferentialDrive robotDrive;
    //can control the two sides with two seperate motors

    public DriveSubsystem(CommandXboxController xboxController ) {
       
    differentialDrive = new DifferentialDrive(leftleader, rightleader);
   
    
    }
       
        //leftfollower.follow(leftleader);

        //SparkMaxConfig followerConfig = new SparkMaxConfig();
       // followerConfig.follow(leftleader);
        //leftfollower.configure(followerConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
      
       
       
         
     

  public Command joyStickSpeed() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return run(
        () -> { runMotor(xboxController.getLeftY(), xboxController.getRightX());
                
          /* one-time action goes here */
        }
        
        );
    }

       // SparkMax rightfollower;
                //rightfollower.configure(
           // new com.revrobotics.spark.config.SparkMaxConfig().follow(leftleader),
           // com.revrobotics.spark.SparkBase.ResetMode.kResetSafeParameters,
           //  com.revrobotics.spark.SparkBase.PersistMode.kPersistParameters
       // );
    public void runMotor(double speed, double rotation) {
    // This passes the joystick values into WPILib's arcade drive math
    differentialDrive.arcadeDrive(speed, rotation);
    }
        
 }

        

 

