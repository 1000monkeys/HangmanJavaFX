package com.kjellvos.school;

/**
 * Created by kjevo on 2/17/17.
 */
public class Letter {
    private char letter;
    private boolean isGuessed, partOfWordToGuess;

    /**
     * Constructor of the class Letters, This sets up all the needed variables.
     * @param letter The letter this class is
     * @param isGuessed if the player has already tried to guess this letter
     * @param partOfWordToGuess if this letter is part of the word the player has to guess
     */
    public Letter(char letter, boolean isGuessed, boolean partOfWordToGuess){
        this.letter = letter;
        this.isGuessed = isGuessed;
        this.partOfWordToGuess = partOfWordToGuess;
    }

    /**
     * Returns the letter this class is
     * @return the letter
     */
    public char getLetter(){
        return letter;
    }

    /**
     * Returns if this letter has already been tried to guess by the player whether it is part of the word or not.
     * @return True or false depending
     */
    public boolean getIsPartOfWordToGuess() {
        return partOfWordToGuess;
    }

    /**
     * Sets whether this letter is part of the word the player is trying to guess
     * @param partOfWordToGuess true or false depending
     */
    public void setPartOfWordToGuess(boolean partOfWordToGuess) {
        this.partOfWordToGuess = partOfWordToGuess;
    }

    /**
     * Sets that this letter has been tried to guess, whether it is part of the word to guess or not.
     * @param isGuessed True if the word has been tried to guess by the player false if it hasn't
     */
    public void setIsGuessed(boolean isGuessed) {
        this.isGuessed = isGuessed;
    }

    /**
     * Returns the variable isGuessed
     * @return boolean depending on whether the word is guessed or not
     */
    public boolean getIsGuessed(){
        return isGuessed;
    }
}
