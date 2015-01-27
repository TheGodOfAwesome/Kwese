/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.jfugue.Player;
/**
 *
 * @author Kuz
 */
public class Sound {
    
    public static void main(String[] args) throws Exception {
    
        Player player = new Player();// Create anew player you only ever need one!
        
        //Play a note string
        player.play("E5s D#5s E5s D#5s E5s B4s D5s C5s ");
        
    }
    
}
