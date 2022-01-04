package controller;

import bo.BoFactory;
import bo.custom.RegisterDetailBO;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import view.tm.RegisterDetailTM;

import java.sql.SQLException;

public class ViewRegisterDetailsController {
    private final RegisterDetailBO registerDetailBO = (RegisterDetailBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.REGISTER_DETAIL);

    public TableView tblList;
    public TableColumn colProgramId;
    public TableColumn colProgramName;
    public TableColumn colDuration;
    public TableColumn colFee;
    public Label lblRegisterId;
    public Label lblTotal;
    public JFXButton btnBack;

    public void loadAllData(String registerId) throws SQLException, ClassNotFoundException {
        lblRegisterId.setText(String.valueOf(registerId));
        String total = null;
        ObservableList<RegisterDetailTM> tmList = FXCollections.observableArrayList();
        for (RegisterDetailTM tempD : registerDetailBO.getAllRegisterDetails(registerId)
        ) {
            tmList.add(new RegisterDetailTM(tempD.getRegisterId(),
                    tempD.getProgramId(),
                    tempD.getDuration(),
                    tempD.getFee(),
                    tempD.getTotal()));
        }
        tblList.setItems(tmList);
        lblTotal.setText("Rs.  " + (total));
    }

    public void backOnAction(ActionEvent actionEvent) {
    }
}
