package ai.flow.launcher;

import ai.flow.common.Params;
import ai.flow.common.ParamsInterface;
import ai.flow.sensor.SensorManager;
import ai.flow.sensor.camera.CameraManager;
import ai.flow.sensor.SensorInterface;
import ai.flow.vision.ModelExecutor;
import ai.flow.vision.ModelExecutorInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Launcher {
    public ModelExecutorInterface modeld;
    public Map<String, SensorInterface> sensors;
    public FlowInitd flowInitd = new FlowInitd();
    public ParamsInterface params = ParamsInterface.getInstance();

    public Launcher(Map<String, SensorInterface> sensors, ModelExecutorInterface modelExecutor){
        this.sensors = sensors;
        this.modeld = modelExecutor;
    }

    public void startModelD() {
        if (!modeld.isRunning())
            modeld.start();
    }

    public void startSensorD() {
        for (String sensorName : sensors.keySet()) {
            if (!sensors.get(sensorName).isRunning())
                sensors.get(sensorName).start();
        }
    }

    public void startPythonDaemons(){
        flowInitd.send(FlowInitd.SIGSTART);
    }

    public void dispose() {
        for (String sensorName : sensors.keySet()) {
            sensors.get(sensorName).stop();
        }
        modeld.stop();
        flowInitd.send(FlowInitd.SIGSTOP);
    }

    public void startAllD() {
        startSensorD();
        startModelD();
        startPythonDaemons();
    }

    public void main(String[] args) throws IOException {
        CameraManager cameraManager = new CameraManager("roadCameraState", 20);
        SensorManager sensorManager = new SensorManager();
        this.sensors = new HashMap<String, SensorInterface>() {{
            put("roadCamera", cameraManager);
            put("motionSensors", sensorManager);
        }};
        this.modeld = new ModelExecutor();
        this.startAllD();
    }
}
