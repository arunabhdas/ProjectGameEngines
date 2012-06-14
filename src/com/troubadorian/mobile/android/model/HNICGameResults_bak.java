/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import java.util.Collection;

public class HNICGameResults_bak
{

    private static HNICGameResults_bak _sharedInstance = null;

    public static synchronized HNICGameResults_bak getInstance()
    {
        if (_sharedInstance == null)
        {
            synchronized (HNICGameResults_bak.class)
            {
                _sharedInstance = new HNICGameResults_bak();
            }
        }
        return _sharedInstance;
    }

    private HNICGameResults_bak()
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