package controller;

import bo.BoFactory;
import bo.custom.RegisterBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import entity.Program;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tm.CartTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentController {
    private final RegisterBO registerBO = (RegisterBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.REGISTER);
    public AnchorPane paymentContext;
    public Label txtDate;
    public Label txtTime;
    public JFXButton btnBack;
    public TableView tblList;
    public Label lblTotal;
    public TextField txtRegisterId;
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXTextField txtBirthday;
    public JFXTextField txtAge;
    public JFXTextField txtGender;
    public JFXTextField txtPhoneNumber;
    public JFXTextField txtEducation;
    public JFXComboBox cmbProgramId;
    public JFXTextField txtProgramName;
    public JFXTextField txtDuration;
    public JFXTextField txtFee;
    public JFXComboBox cmbStudentId;
    public TableColumn colRegisterId;
    public TableColumn colStudentId;
    public TableColumn colProgramId;
    public TableColumn colStudentName;
    public TableColumn colProgramName;
    public TableColumn colPayment;

    public void initialize(){
        try {
            loadStudentIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadProgramIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setStudentData((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbProgramId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setProgramData((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        colRegisterId.setCellValueFactory(new PropertyValueFactory<>("registerId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    private void setProgramData(String programId) throws SQLException, ClassNotFoundException {
        ProgramDTO programDTO = registerBO.searchPrograms(programId);
        if (programDTO == null){
            new Alert(Alert.AlertType.WARNING, "Empty Result");
        }else {
            txtProgramName.setText(programDTO.getProgramName());
            txtDuration.setText(programDTO.getDuration());
            txtFee.setText(String.valueOf(programDTO.getFee()));
        }
    }

    private void setStudentData(String studentId) throws SQLException, ClassNotFoundException {
        StudentDTO studentDTO = registerBO.searchStudents(studentId);
        if (studentDTO == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result");
        } else {
            txtStudentName.setText(studentDTO.getStudentName());
            txtAddress.setText(studentDTO.getAddress());
            txtBirthday.setText(studentDTO.getBirthday());
            txtAge.setText(String.valueOf(studentDTO.getAge()));
            txtGender.setText(studentDTO.getGender());
            txtPhoneNumber.setText(studentDTO.getPhoneNumber());
            txtEducation.setText(studentDTO.getEducation());
        }
    }

   private void loadProgramIds() throws SQLException, ClassNotFoundException {
        List<String> programIds = registerBO.getAllProgramIds();
        cmbProgramId.getItems().addAll(programIds);
    }

   private void loadStudentIds() throws SQLException, ClassNotFoundException {
        List<String> studentIds = registerBO.getAllStudentIds();
        cmbStudentId.getItems().addAll(studentIds);
    }

    public void backOnAction(ActionEvent actionEvent) {
    }

    ObservableList<CartTM> obList = FXCollections.observableArrayList();
    public void addToTableOnAction(ActionEvent actionEvent) {
        String registerId = txtRegisterId.getText();
        String studentId = String.valueOf(cmbStudentId.getValue());
        String programId = String.valueOf(cmbProgramId.getValue());
        String studentName = txtStudentName.getText();
        String programName = txtProgramName.getText();
        double total = Double.parseDouble(txtFee.getText());

        CartTM tm = new CartTM(
                registerId,
                studentId,
                programId,
                studentName,
                programName,
                total
        );

        int rowNumber = isExists(tm);

        if ( rowNumber==-1) {
            obList.add(tm);
        } else {
            CartTM temp = obList.get(rowNumber);
            CartTM newTm = new CartTM(
                    temp.getRegisterId(),
                    temp.getStudentId(),
                    temp.getProgramId(),
                    temp.getStudentName(),
                    temp.getProgramName(),
                    total+temp.getTotal()
            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }

        tblList.setItems(obList);

        calculate();
    }

    private int isExists(CartTM tm) {

        for (int i = 0; i < obList.size(); i++) {
            if (tm.getProgramId().equals(obList.get(i).getProgramId())){
                return i;
            }
        }
        return -1;
    }
    void calculate() {
        double ttl = 0;
        for (CartTM tm : obList
        ) {
            ttl += tm.getTotal();
        }
        lblTotal.setText(ttl+"/=");
    }

    public void confirmOnAction(ActionEvent actionEvent) {
        /*ArrayList<OrderDetailDTO> items= new ArrayList<>();
        for (CartTM tm:obList
        ) {
            items.add (new OrderDetailDTO(txtOrderId.getText(),tm.getItemCode(),tm.getQty(),tm.getUnitPrice()));
        }

        boolean b = saveOrder(txtOrderId.getText(), (String) cmbCustomerId.getValue(),lblDate.getText(),items);
        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Order has been placed successfully").show();
            //showInvoice();
            //setOrderId();
            //clearText();
        } else {
            new Alert(Alert.AlertType.ERROR, "Order has not been placed successfully").show();
        }*/
    }

    /*public boolean saveOrder(String registerId,String registerDate, String register, List<OrderDetailDTO> items) {
        try {
            RegistrationDTO registrationDTO = new RegistrationDTO();
            return purchaseOrderBO.purchaseOrder(ordersDTO);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }*/
}
