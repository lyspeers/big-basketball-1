package com.company;
import java.io.*;
/**
 * Created by da426 on 2/27/18.
 */
public class Bracket {
    /*
    THIS IS ALL SUDO CODE TO REFER TO

    Create a file to write the bracket to.  Round 1 header all the round 1 predictions
    Repeat for rounds 2 3 4 5

    Team have a "win score" calculated in team method or something like that
    the teams are recorded in the text file and then the scores are compared and the winner is also written to the text file

    The text file is cleared before the bracket is written.
     */

    static void build(/*maybe team elements or an arraylist of teams*/) throws IOException{
        //Ignore all the errors I dont know what will be in there yet

        PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
        //x is the arraylist
        for(int i = 0;i<=x.size();i++) { //this is just round 1 maybe a variable to shrink/change the array list with the winning teams
            writer.println(team1);
            writer.println(team2);

            if (team1.compaerTo(team2) < 0) {  //something like this basicly it compares the teams
                writer.println(team1.naame + " wins");
                x.remove(team2);  //I dont know if theis decreases the size of the array list so the next itterations might be a problem
                //also if it does shrink the array list size I can make it work using recursion pretty easily
            } else if (team1.compaerTo(team2) > 0) {
                writer.println(team2.name + " wins");
                x.remove(team1);
            } else   //coin flip if there scores are equal
                if (Math.random() < .5) {
                    writer.println(team1.naame + " wins");
                    x.reomve(team2);
                } else {
                    writer.println(team2.name + " wins");
                    x.reamove(team2);
        }

        }
        writer.close();

    }
}
