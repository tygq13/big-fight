package bigfight.data;

public class DataGetter {
    private DataConfig dataConfig;

    public DataGetter(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
    }

    public int getInitialAttributeTotal() {
        return dataConfig.getInitialAttributeTotal();
    }
}
