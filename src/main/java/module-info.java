module com.example.gui_chat_1313_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gui_chat_1313_1 to javafx.fxml;
    exports com.example.gui_chat_1313_1;
}