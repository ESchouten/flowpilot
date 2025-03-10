package ai.flow.vision;

public class MetaData {
    public float engagedProb = 0.0f;
    public float[] desirePrediction = new float[4*Parser.DESIRE_LEN];
    public float[] desireState = new float[Parser.DESIRE_LEN];
    public DisengagePredictions disengagePredictions = new DisengagePredictions();
    public boolean hardBrakePredicted = false;
}
