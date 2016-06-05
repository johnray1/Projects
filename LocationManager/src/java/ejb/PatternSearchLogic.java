/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import static java.lang.Math.max;
import javax.ejb.Stateless;

/**
 *
 * @author JOHN
 */
@Stateless
public class PatternSearchLogic {
    
       
    
//distance calculation
    public int distance(String input, String locAliaseName) {
        
            input = input.toLowerCase();
            locAliaseName = locAliaseName.toLowerCase();
        // i == 0
        int [] costs = new int [locAliaseName.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= input.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= locAliaseName.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), input.charAt(i - 1) == locAliaseName.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[locAliaseName.length()];
    }

//percentage calculation
       public double percentage(double distance, String input, String locAliaseName) {

        double percent = 0;
        percent = 100 - (distance * 100) / max(input.length(), locAliaseName.length());

        return percent;
    }
    
//decision taking
       public String decision(double percentage, String locAliaseName) {

        String data = "";
        if (percentage >= 50) {

            data = locAliaseName;
        }

        return data;
    }
        
        
}
