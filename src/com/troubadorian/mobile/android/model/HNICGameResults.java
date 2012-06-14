/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import java.util.Collection;
import java.util.List;

public class HNICGameResults
{

   private List<HNICGameResults> results;
   
   private static HNICGameResults _sharedInstance = null;
   
   public static synchronized HNICGameResults getInstance()
   {
       if (_sharedInstance == null)
       {
           synchronized (HNICGameResults.class)
           {
               _sharedInstance = new HNICGameResults();
           }
       }
       return _sharedInstance;
   }
   
   private HNICGameResults()
   {
       super();
   }


   private Collection<HNICGame> HNICGames;

   /**
    * @return the HNICGames
    */
   public Collection<HNICGame> getHNICGames()
   {
       return HNICGames;
   }

   /**
    * @param HNICGames
    *            the HNICGames to set
    */
   public void setHNICGames(Collection<HNICGame> HNICGames)
   {
       this.HNICGames = HNICGames;
   }

   public void addHNICGame(HNICGame HNICGame)
   {
       HNICGames.add(HNICGame);
   }

   public boolean removeHNICGame(HNICGame HNICGame)
   {
       return HNICGames.remove(HNICGame);
   }

   public HNICGame getHNICGame()
   {
       return new HNICGame();
   }
}