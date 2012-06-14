/**
 * HNICBoxScoreDetail.java
 * @author Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import com.google.myjson.annotations.SerializedName;


public class HNICBoxScoreDetail
{

    private String score;

    private String period1score;

    private String period2score;

    private String period3score;
    
    private String overtimeScore;
    
    private String shootoutScore;
    

    @SerializedName("score-attempts")
    private String score_attempts;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "BoxScoreDetail [period1score=" + period1score
                + ", period2score=" 
                + period2score 
                + ", period3score="
                + period3score
                + ", overtimeScore="
                + overtimeScore
                + ", shootoutScore="
                + shootoutScore              
                + ", score=" 
                + score 
                + ", score_attempts="
                + score_attempts 
                + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((period1score == null) ? 0 : period1score.hashCode());
        result = prime * result
                + ((period2score == null) ? 0 : period2score.hashCode());
        result = prime * result
                + ((period3score == null) ? 0 : period3score.hashCode());
        result = prime * result
        + ((overtimeScore == null) ? 0 : overtimeScore.hashCode());
        result = prime * result
        + ((shootoutScore == null) ? 0 : shootoutScore.hashCode());
        result = prime * result + ((score == null) ? 0 : score.hashCode());
        
        result = prime * result
                + ((score_attempts == null) ? 0 : score_attempts.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HNICBoxScoreDetail other = (HNICBoxScoreDetail) obj;
        if (period1score == null)
        {
            if (other.period1score != null)
                return false;
        } else if (!period1score.equals(other.period1score))
            return false;
       
        if (period2score == null)
        {
            if (other.period2score != null)
                return false;
        } else if (!period2score.equals(other.period2score))
            return false;
       
        
        if (period3score == null)
        {
            if (other.period3score != null)
                return false;
        } else if (!period3score.equals(other.period3score))
            return false;
        
        if (overtimeScore == null)
        {
            if (other.overtimeScore != null)
                return false;
        } else if (!overtimeScore.equals(other.overtimeScore))
            return false;
        
        if (shootoutScore == null)
        {
            if (other.shootoutScore != null)
                return false;
        } else if (!shootoutScore.equals(other.shootoutScore))
            return false;
       
        if (score == null)
        {
            if (other.score != null)
                return false;
        } else if (!score.equals(other.score))
            return false;
       
        if (score_attempts == null)
        {
            if (other.score_attempts != null)
                return false;
        } else if (!score_attempts.equals(other.score_attempts))
            return false;
        return true;
    }

    /**
     * @return the score
     */
    public String getScore()
    {
        return score;
    }

    /**
     * @param score
     *            the score to set
     */
    public void setScore(String score)
    {
        this.score = score;
    }

    /**
     * @return the period1score
     */
    public String getPeriod1score()
    {
        return period1score;
    }

    /**
     * @param period1score
     *            the period1score to set
     */
    public void setPeriod1score(String period1score)
    {
        this.period1score = period1score;
    }

    /**
     * @return the period2score
     */
    public String getPeriod2score()
    {
        return period2score;
    }

    /**
     * @param period2score
     *            the period2score to set
     */
    public void setPeriod2score(String period2score)
    {
        this.period2score = period2score;
    }

    /**
     * @return the period3score
     */
    public String getPeriod3score()
    {
        return period3score;
    }

    /**
     * @param period3score
     *            the period3score to set
     */
    public void setPeriod3score(String period3score)
    {
        this.period3score = period3score;
    }

    /**
     * @return the overtimeScore
     */
    public String getOvertimeScore()
    {
        return overtimeScore;
    }

    /**
     * @param overtimeScore
     *            the overtimeScore to set
     */
    public void setOvertimeScore (String overtimeScore)
    {
        this.overtimeScore = overtimeScore;
    }
    
    /**
     * @return the shootOutScore;
     */
    public String getShootOutScore()
    {
        return overtimeScore;
    }

    /**
     * @param shootoutScore
     *            the shootoutScore to set
     */
    public void setShootOutScore (String shootoutScore)
    {
        this.shootoutScore = shootoutScore;
    }
    
    /**
     * @return the score_attempts
     */
    public String getScore_attempts()
    {
        return score_attempts;
    }

    /**
     * @param scoreAttempts
     *            the score_attempts to set
     */
    public void setScore_attempts(String scoreAttempts)
    {
        score_attempts = scoreAttempts;
    }
    

}