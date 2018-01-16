package View;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 *
 * @author danyp
 */
public class TitlePane extends GridPane{
    Label value;
    public TitlePane(){
        Label title = new Label("Cargo Loader:");
        title.setFont(new Font("Arial", 30));
        add(title, 0, 0);
        add(getValuePane(), 0, 1);
        value = new Label("Container value: 0");
        value.setFont(new Font("Arial", 20));
        add(value, 0, 2);
        setAlignment(Pos.CENTER);
        setHalignment(title, HPos.CENTER);
        setHalignment(getValuePane(), HPos.CENTER);
        setHalignment(value, HPos.CENTER);
        setVgap(7.5);
    }
    public GridPane getValuePane(){
        GridPane gridPane = new GridPane();
        Label parcelValueA = new Label("Parcel A value = 3");
        parcelValueA.setFont(new Font("Arial", 20));
        Label parcelValueB = new Label("Parcel B value = 4");
        parcelValueB.setFont(new Font("Arial", 20));
        Label parcelValueC = new Label("Parcel C value = 5");
        parcelValueC.setFont(new Font("Arial", 20));
        gridPane.add(parcelValueA, 0, 0);
        gridPane.add(parcelValueB, 1, 0);
        gridPane.add(parcelValueC, 2, 0);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        
        return gridPane;
    }
    public void setDisplayedValue(int Value){
        value.setText("Container value: " + Value);
    }
}
