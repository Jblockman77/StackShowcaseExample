module StackHolder{
    requires javafx.controls;
    requires javafx.fxml;


    opens StackHolder to javafx.fxml;
    exports StackHolder;
}