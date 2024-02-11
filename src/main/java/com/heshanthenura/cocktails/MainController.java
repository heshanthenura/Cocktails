package com.heshanthenura.cocktails;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class MainController implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private ScrollPane scrollPane;

    int colNum;
    int colSize = 200;
    int rowNum;
    int childNum;
    Logger logger = Logger.getLogger("info");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            childNum = gridPane.getChildren().size();
            scrollPane.widthProperty().addListener((observable, oldValue, newValue) -> {


                colNum = (int) Math.ceil(newValue.intValue() / (double) colSize);

                rowNum = (int) Math.ceil(childNum / (double) colNum);


                if (colNum > gridPane.getColumnCount()) {

                    ColumnConstraints columnConstraints = new ColumnConstraints();
                    gridPane.getColumnConstraints().add(columnConstraints);
                    reArrange();
                } else if (colNum < gridPane.getColumnCount()) {
                    reArrange();
                    gridPane.getColumnConstraints().remove(gridPane.getColumnCount() - 1);
                }

            });
        });
    }


    void reArrange() {
        int numCols = gridPane.getColumnConstraints().size();
        int index = 0;

        for (Node child : gridPane.getChildren()) {
            int row = index / numCols; // Calculate row index based on the total number of columns
            int col = index % numCols; // Calculate column index based on the total number of columns
            GridPane.setRowIndex(child, row);
            GridPane.setColumnIndex(child, col);
            index++;
        }
    }



}
