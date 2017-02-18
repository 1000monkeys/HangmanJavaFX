package com.kjellvos.school;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;
import java.util.Random;

/**
 * Created by kjevo on 2/17/17.
 */
public class HangmanWithUI {
    private String alphabetString;
    private Letter[] letters;
    private char[] alphabet;
    private String wordPlayerTriesToGuess;
    private char[] wordPlayerTriesToGuessCharArray;
    private Main main;

    /**
     * Sets up the string of the alphabet and makes a char array of it in another variable,
     * then creates all the letters in array in order of alphabetical position
     */
    public HangmanWithUI(Main main){
        this.main = main;
        alphabetString = "abcdefghijklmnopqrstuvwxyz";
        alphabet = alphabetString.toCharArray();

        int i = 0;
        letters = new Letter[26];
        while (i < 26){
            letters[i] = new Letter(alphabet[i], false, false);
            i++;
        }
    }

    /**
     * Sets up everything needed to start the game besides making the letters classes
     */
    public void setupGame(){
        setupLettersWithRandomWord();
        main.setupCanvasWithWords(main.getGraphicsContext(), getLetters());
        main.createHangMan(main.getGraphicsContext());
    }

    /**
     * TODO
     */
    public void takeInput(char letterThePlayerTriedToGuess){
        int alphabeticalPosition = getAlphabetPosition(letterThePlayerTriedToGuess);
        if (!letters[alphabeticalPosition].getIsGuessed()) {
            letters[alphabeticalPosition].setIsGuessed(true);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("OOPS!");
            alert.setHeaderText(null);
            alert.setContentText("Deze heb je al geprobeerd!");
            alert.showAndWait();
        }
        if (checkIfEntireWordGuessed()){
            reRender();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Je hebt het woord geraden!");
            alert.setHeaderText("Wil je Nog een keer spelen?");
            alert.setContentText(null);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                resetLetters();
                setupLettersWithRandomWord();
                reRender();
            } else {
                System.exit(0);
            }
        }else{
            checkIfTooManyMistakes();
            reRender();
        }
    }


    /**
     * TODO
     * @return
     */
    public int getAmountOfMistakes(){
        int countOfMistakes = 0;
        for(int i = 0; i < 26; i++) {
            if (letters[i].getIsGuessed() && letters[i].getIsPartOfWordToGuess() == false) {
                countOfMistakes++;
            }
        }
        return countOfMistakes;
    }

    /**
     * TODO
     */
    private void checkIfTooManyMistakes(){
        if(getAmountOfMistakes() > 10){
            reRender();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Teveel fout!");
            alert.setHeaderText("Wil je opnieuw beginnen?");
            alert.setContentText(null);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                resetLetters();
                setupLettersWithRandomWord();
                reRender();
            } else System.exit(0);
        }
    }

    /**
     * Gets the position of a passed character in the alphabet
     * @param letter the letter to get the alphabetical index of
     * @return the index of the passed letter
     */
    public int getAlphabetPosition(char letter){
        return alphabetString.indexOf(letter);
    }

    /**
     * Returns the letters array to another class for for example
     * @return Letters array
     */
    public Letter[] getLetters() {
        return letters;
    }

    /**
     * Gets a random word then sets up the letters with this word.
     */
    public void setupLettersWithRandomWord() {
        wordPlayerTriesToGuess = getRandomWord();
        wordPlayerTriesToGuessCharArray = wordPlayerTriesToGuess.toCharArray();

        int i = 0;
        while (i < wordPlayerTriesToGuessCharArray.length) {
            int i2 = 0;
            while (i2 < 26) {
                if (letters[i2].getLetter() == wordPlayerTriesToGuessCharArray[i]) {
                    letters[i2].setPartOfWordToGuess(true);
                }
                i2++;
            }
            i++;
        }
    }

    /**
     * Resets the letters to the state before a word was picked
     */
    private void resetLetters(){
        for (int i = 0; i < 26; i++){
            letters[i].setIsGuessed(false);
            letters[i].setPartOfWordToGuess(false);
        }
    }

    /**
     * TODO
     */
    public char[] getWordPlayerTriesToGuessCharArray() {
        return wordPlayerTriesToGuessCharArray;
    }

    /**
     * TODO
     */
    private void reRender(){
        main.createBorderAndBackground(main.getGraphicsContext());
        main.setupCanvasWithWords(main.getGraphicsContext(), letters);
        main.createHangMan(main.getGraphicsContext());
    }

    /**
     * TODO
     */
    private boolean checkIfEntireWordGuessed() {
        int i = 0, numberOfGoodGuesses = 0;
        while (i < wordPlayerTriesToGuessCharArray.length) {
            if (letters[getAlphabetPosition(wordPlayerTriesToGuessCharArray[i])].getIsGuessed()) {
                numberOfGoodGuesses++;
            }
            i++;
        }
        if(numberOfGoodGuesses == wordPlayerTriesToGuessCharArray.length){
            return true;
        }
        return false;
    }

    /**
     * Makes an array consisting of words and then creates a number consisting of a number between 0 and the number of itmes in the array -1
     * @return the word to guess
     */
    private String getRandomWord(){
        String[] words = new String[50];
        words[0] = "grafeem";
        words[1] = "tjiftjaf";
        words[2] = "maquette";
        words[3] = "kitsch";
        words[4] = "pochet";
        words[5] = "convocaat";
        words[6] = "jakkeren";
        words[7] = "collaps";
        words[8] = "zuivel";
        words[9] = "cesium";
        words[10] = "voyant";
        words[11] = "spitten";
        words[12] = "pancake";
        words[13] = "gietlepel";
        words[14] = "karwats";
        words[15] = "dehydreren";
        words[16] = "viswijf";
        words[17] = "flater";
        words[18] = "cretonne";
        words[19] = "sennhut";
        words[20] = "tichel";
        words[21] = "wijten";
        words[22] = "cadeau";
        words[23] = "trotyl";
        words[24] = "chopper";
        words[25] = "pielen";
        words[26] = "vigeren";
        words[27] = "vrijuit";
        words[28] = "dimorf";
        words[29] = "kolchoz";
        words[30] = "janhen";
        words[31] = "plexus";
        words[32] = "borium";
        words[33] = "ontweien";
        words[34] = "quiche";
        words[35] = "ijverig";
        words[36] = "mecenaat";
        words[37] = "falset";
        words[38] = "telexen";
        words[39] = "hieruit";
        words[40] = "femelaar";
        words[41] = "cohesie";
        words[42] = "exogeen";
        words[43] = "plebejer";
        words[44] = "opbouw";
        words[45] = "zodiak";
        words[46] = "volder";
        words[47] = "vrezen";
        words[48] = "convex";
        words[49] = "verzenden";

        Random random = new Random();
        String word = words[random.nextInt(50)];
        if(Main.DEBUG){
            System.out.println(word);
        }
        return word;
    }
}
