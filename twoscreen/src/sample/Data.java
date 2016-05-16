package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by ali on 5/6/16.
 */
public class Data {

    Vector<ware> Warehouse = new Vector<ware>();

    Vector<cert> Certhouse = new Vector<cert>();

    public ware tmp1 = new ware();
    public cert tmp2 = new cert();

    public List<String> warenames = new ArrayList<>();
    public List<String> certnames = new ArrayList<>();

    public ObservableList<String> wareItems = FXCollections.observableArrayList (warenames);
    public ObservableList<String> certItems = FXCollections.observableArrayList (certnames);

    private static Data theData = new Data();

    public static Data getData() {
        return theData;
    }

}