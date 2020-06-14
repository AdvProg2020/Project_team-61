package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.off.DiscountCode;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class AddDiscountFx {


    private DiscountCode newDiscountCode;
    @FXML
    public ImageView Exit;
    @FXML
    public ImageView Back;
    @FXML
    public ImageView Account;
    @FXML
    public TextField discountIdTextField;
    @FXML
    public Spinner timesOfUseSpinner;
    @FXML
    public Spinner discountAmountSpinner;
    @FXML
    public DatePicker EndOfDiscountDatePicker;
    @FXML
    public DatePicker StartOfDiscountDatePicker;
    @FXML
    public Label discountIdAlertLabel;
    @FXML
    public Label StartDiscountAlertLabel;
    @FXML
    public Label EndDiscountAlertLabel;

    public void setDiscountIdTextField() {
        String input = discountIdTextField.getText();
        //newDiscountCode.
    }

    public void setDiscountAmount() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 100, 1, 5);
        discountAmountSpinner = new Spinner();
        discountAmountSpinner.setValueFactory(valueFactory);
        discountAmountSpinner.setEditable(true);
        newDiscountCode.setDiscountAmount(Integer.parseInt(discountAmountSpinner.getValue().toString()));

    }

    //?annonation lazeme?
    @FXML
    public void setTimesOfUseSpinner() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 2, 1);
        timesOfUseSpinner = new Spinner();
        timesOfUseSpinner.setValueFactory(valueFactory);
        timesOfUseSpinner.setEditable(true);
        newDiscountCode.setTotalTimesOfUse(Integer.parseInt(timesOfUseSpinner.getValue().toString()));

    }

    @FXML
    public void setStartOfDiscountDate() {
        if (discountIdTextField.getText() == null) {
            StartOfDiscountDatePicker.setDisable(true);
        } else {
            LocalDate inputLocalDate;
            inputLocalDate = StartOfDiscountDatePicker.getValue();
            Date inputDate;
            Date now = new Date();
            ZoneId defaultZoneId = ZoneId.systemDefault();
            inputDate = Date.from(inputLocalDate.atStartOfDay(defaultZoneId).toInstant());
            if (now.after(inputDate)) {
                StartDiscountAlertLabel.setText("Your date must be after today!");
            } else
                newDiscountCode.setStartOfDiscountPeriod(inputDate);
        }

    }

    public void setEndOfDiscountDate() {
        if (StartOfDiscountDatePicker.getValue() == null) {
            EndOfDiscountDatePicker.setDisable(true);
        } else {
            LocalDate inputLocalDate;
            inputLocalDate = StartOfDiscountDatePicker.getValue();
            Date inputDate;
            Date now = new Date();
            ZoneId defaultZoneId = ZoneId.systemDefault();
            inputDate = Date.from(inputLocalDate.atStartOfDay(defaultZoneId).toInstant());
            if (inputDate.before(now) || inputDate.before(newDiscountCode.getStartOfDiscountPeriod())) {
                EndDiscountAlertLabel.setText("End of Discount Date period is invalid!");
            } else
                newDiscountCode.setEndOfDiscountPeriod(inputDate);
        }

    }

    public void processBack(MouseEvent mouseEvent) {
    }

    public void processExit(MouseEvent mouseEvent) {
    }

    public void gotoAccount(MouseEvent mouseEvent) {
    }

   /* @FXML

    @FXML
    @FXML
    @FXML
    @FXML
    @FXML
    @FXML
    @FXML*/


}

