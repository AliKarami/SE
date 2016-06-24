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

    dbHandler dbh = new dbHandler();

    char curUserType = 'N';

    Vector<ware> Warehouse = new Vector<ware>();
    Vector<ware> cWarehouse = new Vector<ware>();

    Vector<String> Certhouse = new Vector<String>();

    Vector<String> rWarehouse = new Vector<String>();
    Vector<String> rManhouse = new Vector<String>();
    Vector<String> rCerthouse = new Vector<String>();

    public ware tmp1 = new ware();
    public ware tmp2 = new ware();

    public List<String> warenames = new ArrayList<String>();
    public List<String> certnames = new ArrayList<String>();
    public List<String> cwarenames = new ArrayList<String>();
    public List<String> rwarenames = new ArrayList<String>();
    public List<String> rmannames = new ArrayList<String>();
    public List<String> rcertnames = new ArrayList<String>();

    public ObservableList<String> wareItems = FXCollections.observableArrayList (warenames);
    public ObservableList<String> certItems = FXCollections.observableArrayList (certnames);
    public ObservableList<String> cwareItems = FXCollections.observableArrayList (cwarenames);
    public ObservableList<String> rwareItems = FXCollections.observableArrayList (rwarenames);
    public ObservableList<String> rmanItems = FXCollections.observableArrayList (rmannames);
    public ObservableList<String> rcertItems = FXCollections.observableArrayList (rcertnames);

    private static Data theData = new Data();

    public static Data getData() {
        return theData;
    }

}