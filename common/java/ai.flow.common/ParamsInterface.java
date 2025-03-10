package ai.flow.common;

import java.util.HashMap;
import java.util.Map;

public abstract class ParamsInterface {
    public static final int PERSISTENT = 0;
    public static final int  CLEAR_ON_START = 1;

    public static final Map<String, Integer> keys = new HashMap<String, Integer>() {{
        put("CalibrationParams", PERSISTENT);
        put("CameraMatrix", PERSISTENT);
        put("DistortionCoefficients", PERSISTENT);
        put("ModelDReady", CLEAR_ON_START);
        put("FlowinitReady", PERSISTENT);
        put("FlowpilotPID", CLEAR_ON_START);
        put("ControlsReady", CLEAR_ON_START);
    }};

    public void setDefaults() {
        for (String key : keys.keySet()) {
            if (keys.get(key) == CLEAR_ON_START)
                deleteKey(key);
        }
    }

    public static ParamsInterface getInstance() {
        if (System.getenv("USE_PARAMS_CLIENT") != null)
            return new ParamsClient();
        return new Params();
    }

    public void putInt(String key, int value){}
    public void putFloat(String key, float value){}
    public void putShort(String key, short value){}
    public void putLong(String key, long value){}
    public void putBool(String key, boolean value){}
    public void putDouble(String key, double value){}
    public void put(String key, byte[] value){}
    public void put(String key, String value){}
    public byte[] getBytes(String key){return null;}
    public int getInt(String key){return 0;}
    public float getFloat(String key){return 0;}
    public short getShort(String key){return 0;}
    public long getLong(String key){return 0;}
    public double getDouble(String key){return 0;}
    public String getString(String key){return null;}
    public boolean getBool(String key){return false;}
    public boolean exists(String key){return false;}
    public void deleteKey(String key){};
    public boolean existsAndCompare(String key, boolean value){return false;}
    public void blockTillExists(String Key) throws InterruptedException{}
}
