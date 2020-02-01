package dukebot.gui;

import dukebot.Duke;
import dukebot.gui.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * Controller for dukebot.gui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_default.png"));
    private Image backgroundImage = new Image(this.getClass().getResourceAsStream("/images/heart_background.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.widthProperty().addListener((o) -> {
            Node vp = scrollPane.lookup(".viewport");
            vp.setStyle("-fx-background-color:transparent;");
        });
        scrollPane.setBackground(new Background(
                new BackgroundImage(backgroundImage,
                        BackgroundRepeat.REPEAT,
                        BackgroundRepeat.REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(BackgroundSize.AUTO,
                                BackgroundSize.AUTO,
                                false,
                                false,
                                false,
                                true
                        ))));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void printCurrentMessage() {
        String currentMessage = duke.getUi().getGuiOutput();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(currentMessage, duke.getUi().getDukeExpression().getDukeImage())
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, duke.getUi().getDukeExpression().getDukeImage())
        );
        userInput.clear();
    }
}