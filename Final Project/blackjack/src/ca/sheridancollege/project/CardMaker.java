/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ca.sheridancollege.project;

/**
 *SYST 17796
 *
 * @author Kevin (Zheng Yi) Wang
 * This was made just so it can make a card and add it to the group of cards class
 */
public class CardMaker extends Card{
    public CardMaker(String suit, String value,int intValue){
        super(suit, value, intValue);
    }
    
    //Returns the card with Value of Suit ex: TEN of CLOVERS
    @Override
    public String toString() {
        return value +" of "+ suit;
    }
}