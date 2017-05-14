package kr.edcan.getitpouch;

/**
 * Created by jaewookahn on 14/05/2017.
 */

public class Data {

    private static Data instance;

    public String barcodeData;

    public interface DataChangeListener {
        public void onDataChange();
    }

    public DataChangeListener listener;

    private Data() {
        instance = this;
    }

    public static final Data getInstance() {
        return instance != null ? instance : new Data();
    }
}
