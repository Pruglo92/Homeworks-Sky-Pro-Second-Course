package Program;

public abstract class WheeledVehicle {
    private final String modelName;
    private final int wheelsCount;

    protected WheeledVehicle(String modelName, int wheelsCount) {
        this.modelName = modelName;
        this.wheelsCount = wheelsCount;
    }

    public String getModelName() {
        return modelName;
    }

    public int getWheelsCount() {
        return wheelsCount;
    }
}
