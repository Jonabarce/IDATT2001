module Wargames {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;

    exports edu.ntnu.idatt2001;
    exports edu.ntnu.idatt2001.GUI;
    exports edu.ntnu.idatt2001.Units;

    opens edu.ntnu.idatt2001;
    opens edu.ntnu.idatt2001.GUI;
    opens edu.ntnu.idatt2001.Units;
}