package StackHolder;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StackVController {
    String all_words ="";
    StackRL<String> undo = new StackRL<>();
    StackRL<String> redo = new StackRL<>();
    @FXML
    private Label docText, undoTop, redoTop;

    @FXML
    private TextField addANewWord;

    @FXML
    private Button addWord, undoButton, redoButton;

    @FXML
    protected void onAddWordButtonClick() {
        undo.push(addANewWord.getText());
        undoTop.setText(undo.peek());
        undoButton.setDisable(false);

        while(!redo.isEmpty()){ // O(n) remover
            redo.pop();
        }
        redoButton.setDisable(true);
        redoTop.setText("");
        // disable Redo button because there isn't anything to Redo.

        all_words += undo.peek() + " "; // uses the top of the stack and adds it to the String
        docText.setText(all_words);
        addANewWord.clear(); // clear textfield
    }

    @FXML
    protected void onUndoButtonClick(){
        String undidWord = undo.pop();
        if(undo.isEmpty()){ // Disable the Undo button because there isn't anything in the Undo stack.
            undoTop.setText("");
            undoButton.setDisable(true);
        } else{
            undoTop.setText(undo.peek());
            undoButton.setDisable(false);
        }

        redo.push(undidWord);
        if(redo.isEmpty()){ //Here just in case the program SOMEHOW breaks, haven't had an issue.
            redoTop.setText("");
            redoButton.setDisable(true);
        }else{
            redoTop.setText(redo.peek());
            redoButton.setDisable(false);
        }
        //odd math, but basically it just takes the word that we popped out and then subtracts the total String length with it +1 because I added " "
        all_words = all_words.substring(0, (all_words.length() - (undidWord.length()+1)));
        docText.setText(all_words);
    }

    @FXML
    protected void onRedoButtonClick(){
        String redidWord = redo.pop();

        if(redo.isEmpty()){ // Null checker, if the Redo stack is Empty it will disable the Redo button.
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
