/**
 * Author : Arunabh Das
 */
package com.troubadorian.mobile.android.model;

import java.util.List;

import com.google.myjson.annotations.SerializedName;

public class HNICCompletedGameResultsResponse
{

    public String away;

    public String home;

    @SerializedName("start-date-time")
    public String start_date_time;

    /* public List<String> results; */

    @Override
    public String toString()
    {
        return "HNICCompletedGameResultsResponse [away=" + away + ", home="
                + home + ", start_date_time=" + start_date_time + ", status="
                + "]";
    }

    public List<HNICResult>

    results;

    public List<HNICResult> getHNICResults()
    {
        return this.results;
    }

    public String getFormattedOutputForDebugging()
    {
        String resultstring = " ";

        for (HNICResult result : this.results)
        {

            if (result.getPeriod().compareTo("1") == 0)
            {
                resultstring += "Period 1x " + "[period=" + result.getPeriod()
                        + ", type=" + result.getType() + ", penaltytype="
                        + result.getPenaltyType() + ", time="
                        + result.getTime() + ", team=" + result.getTeam()
                        + ", player=" + result.getPlayer() + ", action="
                        + result.getAction() + ", length=" + result.getLength()
                        + "]";

            }

            else if (result.getPeriod() == "2")
            {
                resultstring += "Period 2 " + "[period=" + result.getPeriod()
                        + ", type=" + result.getType() + ", penaltytype="
                        + result.getPenaltyType() + ", time="
                        + result.getTime() + ", team=" + result.getTeam()
                        + ", player=" + result.getPlayer() + ", action="
                        + result.getAction() + ", length=" + result.getLength()
                        + "]";

            } else
            {

                resultstring += "Period : " + result.getPeriod() + "\n";

                if (result.getType() != null)
                {

                    resultstring += " Type: " + result.getType() + " " + "\n";
                }

                if (result.getPowerplay() != null)
                {
                    resultstring += " Powerplay: " + result.getPowerplay()
                            + " " + "\n";
                }

                if (result.getPenaltyType() != null)
                {
                    resultstring += "PenaltyType : " + result.getPenaltyType()
                            + " " + "\n";
                }

                if (result.getTime() != null)
                {
                    resultstring += " Time : " + result.getTime() + " " + "\n";
                }

                if (result.getTeam() != null)
                {
                    resultstring += "Team : " + result.getTeam() + " " + "\n";
                }

                if (result.getPlayer() != null)
                {
                    resultstring += "Player : " + result.getPlayer() + " "
                            + "\n";
                }

                if (result.getAction() != null)
                {
                    resultstring += "Action : " + result.getAction() + " "
                            + "\n";
                }

                if (result.getAction() != null)
                {
                    resultstring += "Action : " + result.getAction() + " "
                            + "\n";
                }

                if (result.getLength() != null)
                {
                    resultstring += "Action : " + result.getLength() + " "
                            + "\n";
                }

            }

        }

        return resultstring;
    }

    public String getFormattedOutput()
    {
        String resultstring = " ";

        for (HNICResult result : this.results)
        {

            if (result.getPeriod() == "1")
            {
                resultstring += "Period 1x " + "[period=" + result.getPeriod()
                        + ", type=" + result.getType() + ", penaltytype="
                        + result.getPenaltyType() + ", time="
                        + result.getTime() + ", team=" + result.getTeam()
                        + ", player=" + result.getPlayer() + ", action="
                        + result.getAction() + ", length=" + result.getLength()
                        + "]";

            }

            else if (result.getPeriod() == "2")
            {
                resultstring += "Period 2 " + "[period=" + result.getPeriod()
                        + ", type=" + result.getType() + ", penaltytype="
                        + result.getPenaltyType() + ", time="
                        + result.getTime() + ", team=" + result.getTeam()
                        + ", player=" + result.getPlayer() + ", action="
                        + result.getAction() + ", length=" + result.getLength()
                        + "]";

            } else
            {

                resultstring += "Period : " + result.getPeriod() + "\n";

                if (result.getType() != null)
                {

                    resultstring += "Type: " + result.getType() + " " + "\n";
                }

                if (result.getPowerplay() != null)
                {
                    resultstring += "Powerplay: " + result.getPowerplay() + " "
                            + "\n";
                }

                if (result.getPenaltyType() != null)
                {
                    resultstring += "PenaltyType : " + result.getPenaltyType()
                            + " " + "\n";
                }

                if (result.getTime() != null)
                {
                    resultstring += "Time : " + result.getTime() + " " + "\n";
                }

                if (result.getTeam() != null)
                {
                    resultstring += "Team : " + result.getTeam() + " " + "\n";
                }

                if (result.getPlayer() != null)
                {
                    resultstring += "Player : " + result.getPlayer() + " "
                            + "\n";
                }

                if (result.getAction() != null)
                {
                    resultstring += "Action : " + result.getAction() + " "
                            + "\n";
                }

                if (result.getAction() != null)
                {
                    resultstring += "Action : " + result.getAction() + " "
                            + "\n";
                }

                if (result.getLength() != null)
                {
                    resultstring += "Action : " + result.getLength() + " "
                            + "\n";
                }

            }

        }

        return resultstring;
    }

    public String getPeriodOne()
    {
        String resultstring = " ";

        for (HNICResult result : this.results)
        {

            if (result.getPeriod().compareTo("1") == 0)
            {

               /* resultstring += "\n" + "Period : " + result.getPeriod() + "\n";*/

                if (result.getType() != null)
                {

                    resultstring += "\n" + result.getType() + " - " ;
                }
                
                if (result.getTime() != null)
                {
                    
                    int elapsed = Integer.parseInt(result.getTime());
                    int elapsedminutes = elapsed / 60;
                    int elapsedseconds = elapsed % 60;
                    
                    resultstring +=   elapsedminutes + ":"  + elapsedseconds ;
                }
                
                if (result.getTeam() != null)
                {
                    resultstring += " (" + result.getTeam() +  ") ";
                }
                
                if (result.getPlayer() != null)
                {
                    resultstring += " " + result.getPlayer() + " ";
                }

/*                if (result.getPowerplay() != null)
                {
                    resultstring += " " + result.getPowerplay()
                            + " " ;
                }*/

           /*     if (result.getPenaltyType() != null)
                {
                    resultstring += " (" + result.getPenaltyType()
                            + ")" ;
                }*/


                if (result.getAction() != null)
                {
                    resultstring += "  (" + result.getAction() + ") "; 
                }


           /*     if (result.getLength() != null)
                {
                    resultstring += "Action : " + result.getLength() + " "
                            + "\n";
                }*/
                
                resultstring += "\n";

            }

        }

        return resultstring;
    }
    
    public String getPeriodOneHTML()
    {
        String resultstring = " ";

        for (HNICResult result : this.results)
        {

            if (result.getPeriod().compareTo("1") == 0)
            {

               /* resultstring += "\n" + "Period : " + result.getPeriod() + "\n";*/

                if (result.getType() != null)
                {

                    resultstring += "\n" + result.getType() + " - " + "\n";
                }
                
                if (result.getTime() != null)
                {
                    resultstring += "Time : " + result.getTime() + " " + "\n";
                }
                
                if (result.getTeam() != null)
                {
                    resultstring += "Team : " + result.getTeam() + " " + "\n";
                }

                if (result.getPowerplay() != null)
                {
                    resultstring += "Powerplay: " + result.getPowerplay()
                            + " " + "\n";
                }

                if (result.getPenaltyType() != null)
                {
                    resultstring += "PenaltyType : " + result.getPenaltyType()
                            + " " + "\n";
                }

                

                

                if (result.getPlayer() != null)
                {
                    resultstring += "Player : " + result.getPlayer() + " "
                            + "\n";
                }

                if (result.getAction() != null)
                {
                    resultstring += "Action : " + result.getAction() + " "
                            + "\n";
                }

                if (result.getAction() != null)
                {
                    resultstring += "Action : " + result.getAction() + " "
                            + "\n";
                }

                if (result.getLength() != null)
                {
                    resultstring += "Action : " + result.getLength() + " "
                            + "\n";
                }

            }

        }

        return resultstring;
    }

    public String getPeriodTwo()
    {
        String resultstring = " ";

        for (HNICResult result : this.results)
        {

            if (result.getPeriod().compareTo("2") == 0)
            {

                /* resultstring += "\n" + "Period : " + result.getPeriod() + "\n";*/

                if (result.getType() != null)
                {

                    resultstring += "\n" + result.getType() + " - " ;
                }
                
                if (result.getTime() != null)
                {
                    
                    int elapsed = Integer.parseInt(result.getTime());
                    int elapsedminutes = elapsed / 60;
                    int elapsedseconds = elapsed % 60;
                    
                    resultstring +=   elapsedminutes + ":"  + elapsedseconds ;
                }
                
                if (result.getTeam() != null)
                {
                    resultstring += " (" + result.getTeam() +  ") ";
                }
                
                if (result.getPlayer() != null)
                {
                    resultstring += " " + result.getPlayer() + " ";
                }

  /*              if (result.getPowerplay() != null)
                {
                    resultstring += " " + result.getPowerplay()
                            + " " ;
                }*/

             /*   if (result.getPenaltyType() != null)
                {
                    resultstring += " (" + result.getPenaltyType()
                            + ")" ;
                }*/


                if (result.getAction() != null)
                {
                    resultstring += " (" + result.getAction() + ")"; 
                }

                
                resultstring += "\n";

           /*     if (result.getLength() != null)
                {
                    resultstring += "Action : " + result.getLength() + " "
                            + "\n";
                }*/

            }

        }

        return resultstring;
    }

    public String getPeriodThree()
    {
        String resultstring = " ";

        for (HNICResult result : this.results)
        {

            if (result.getPeriod().compareTo("3") == 0)
            {

                /* resultstring += "\n" + "Period : " + result.getPeriod() + "\n";*/

                if (result.getType() != null)
                {

                    resultstring += "\n" + result.getType() + " - " ;
                }
                
                if (result.getTime() != null)
                {
                    
                    int elapsed = Integer.parseInt(result.getTime());
                    int elapsedminutes = elapsed / 60;
                    int elapsedseconds = elapsed % 60;
                    
                    resultstring +=   elapsedminutes + ":"  + elapsedseconds ;
                }
                
                if (result.getTeam() != null)
                {
                    resultstring += " (" + result.getTeam() +  ") ";
                }
                
                if (result.getPlayer() != null)
                {
                    resultstring += " " + result.getPlayer() + " ";
                }

              /*  if (result.getPowerplay() != null)
                {
                    resultstring += " " + result.getPowerplay()
                            + " " ;
                }*/

             /*   if (result.getPenaltyType() != null)
                {
                    resultstring += " (" + result.getPenaltyType()
                            + ")" ;
                }
*/

                if (result.getAction() != null)
                {
                    resultstring += " (" + result.getAction() + ")"; 
                }

                
                resultstring += "\n";

           /*     if (result.getLength() != null)
                {
                    resultstring += "Action : " + result.getLength() + " "
                            + "\n";
                }*/

            }

        }

        return resultstring;
    }
    
    public String getPeriodOT()
    {
        String resultstring = " ";

        for (HNICResult result : this.results)
        {

            if (result.getPeriod().compareTo("OT") == 0)
            {

                /* resultstring += "\n" + "Period : " + result.getPeriod() + "\n";*/

                if (result.getType() != null)
                {

                    resultstring += "\n" + result.getType() + " - " ;
                }
                
                if (result.getTime() != null)
                {
                    
                    int elapsed = Integer.parseInt(result.getTime());
                    int elapsedminutes = elapsed / 60;
                    int elapsedseconds = elapsed % 60;
                    
                    resultstring +=   elapsedminutes + ":"  + elapsedseconds ;
                }
                
                if (result.getTeam() != null)
                {
                    resultstring += " (" + result.getTeam() +  ") ";
                }
                
                if (result.getPlayer() != null)
                {
                    resultstring += " " + result.getPlayer() + " ";
                }

              /*  if (result.getPowerplay() != null)
                {
                    resultstring += " " + result.getPowerplay()
                            + " " ;
                }*/

             /*   if (result.getPenaltyType() != null)
                {
                    resultstring += " (" + result.getPenaltyType()
                            + ")" ;
                }
*/

                if (result.getAction() != null)
                {
                    resultstring += " (" + result.getAction() + ")"; 
                }

                
                resultstring += "\n";

           /*     if (result.getLength() != null)
                {
                    resultstring += "Action : " + result.getLength() + " "
                            + "\n";
                }*/

            }

        }

        return resultstring;
    }
    
}
