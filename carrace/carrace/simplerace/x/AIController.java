package simplerace.x;
import simplerace.*;

public class AIController implements Controller, Constants
{
    private Integer _command = 0;
    private SensorModel _inputs;
    public void reset() {}

    public int control (SensorModel inputs)
    {
      _inputs = inputs;
       // try
       // {
       //     Thread.sleep(100); // 10秒(1万ミリ秒)間だけ処理を止める
       // } catch (InterruptedException e) {
       // }
       var angleNextPoint = _inputs.getAngleToNextWaypoint();
       var angleNextNextPoint = _inputs.getAngleToNextWaypoint();
       var distanceNextPoint = _inputs.getDistanceToNextWaypoint();
       var distanceNextNextPoint = _inputs.getDistanceToNextNextWaypoint();
       var positionNextPoint = _inputs.getNextWaypointPosition();
       var positionNextNextPoint = _inputs.getNextWaypointPosition();

       var position = _inputs.getPosition(); // Vector2 [x,y]
       var velocity = _inputs.getVelocity(); // Vector2 [x,y]
       var orientation = _inputs.getOrientation();


		    _command = neutral;
        // System.out.println(distanceNextPoint );

        if(distanceNextPoint < 0.1f)
        {
            goNextNextTarget();
            return _command;
        }

        if(distanceNextPoint < 0.05f)
        {
            // System.out.println("ターゲットを更新" +positionNextPoint );
        }
        goNextTarget();

        return _command;
    }

    private void goNextTarget()
    {
      if(_inputs.getAngleToNextWaypoint() > 0)
      {
          _command=forwardleft;
      }
      else
      {
          _command=forwardright;
      }
    }

    private void goNextNextTarget(){
      if(_inputs.getAngleToNextNextWaypoint() > 0)
      {
          _command=forwardleft;
      }
      else
      {
          _command=forwardright;
      }
    }

}
