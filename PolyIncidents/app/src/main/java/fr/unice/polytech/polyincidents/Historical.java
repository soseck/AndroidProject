package fr.unice.polytech.polyincidents;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Donélia on 21/05/2018.
 */

public class Historical {

    List<String> hist = new ArrayList<>();

     public Historical() {
         hist.add("bonbon");
     }

     public List<String> getHist() {
         return hist;
     }
}
