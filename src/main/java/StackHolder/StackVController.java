package StackHolder;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class StackVController {
    String all_words ="";
    StackRL<String> undo = new StackRL<>();
    StackRL<String> redo = new StackRL<>();
    @FXML
    private Label docText, undoTop, redoTop;

    @FXML
    private TextField addANewWord;

    @FXML
    private Button addWord;
    @FXML
    private Button undoButton;
    @FXML
    private Button redoButton;

    @FXML
    protected void onAddWordButtonClick() {
        undo.push(addANewWord.getText());
        undoTop.setText(undo.peek());
        undoButton.setDisable(false);

        while(!redo.isEmpty()){
            redo.pop();
        }
        redoButton.setDisable(true);

        redoTop.setText("");
        docText.setText("");
        all_words += undo.peek() + " ";
        docText.setText(all_words);
        addANewWord.clear();
    }

    @FXML
    protected void onUndoButtonClick(){
        String undidWord = undo.pop();
        if(undo.isEmpty()){
            undoTop.setText("");
            undoButton.setDisable(true);
        } else{
            undoTop.setText(undo.peek());
            undoButton.setDisable(false);
        }

        redo.push(undidWord);
        if(redo.isEmpty()){
            redoTop.setText("");
            redoButton.setDisable(true);
        }else{
            redoTop.setText(redo.peek());
            redoButton.setDisable(false);
        }

        all_words = all_words.substring(0, (all_words.length() - (undidWord.length()+1)));
        docText.setText(all_words);
    }

    @FXML
    protected void onRedoButtonClick(){
        String redidWord = redo.pop();

        if(redo.isEmpty()){
            redoTop.setText("");
            redoButton.setDisable(true);
        } else{
            redoButton.setDisable(false);
            redoTop.setText(redo.peek());
        }

        undo.push(redidWord);
        undoButton.setDisable(false);
        undoTop.setText(redidWord);

        all_words += redidWord + " ";
        docText.setText(all_words);
    }
}
