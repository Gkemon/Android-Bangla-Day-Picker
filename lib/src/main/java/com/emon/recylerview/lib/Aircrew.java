package com.emon.recylerview.lib;

public class Aircrew {
    private String name;
    private String callSign;
    private int missions;
    private String status;

    public Aircrew (String name, String callSign)
    {
        this.name = name;
        this.callSign = callSign;
        this.missions = 0;
        setStatus();
    }

    public Aircrew (String name, String callSign, int missions)
    {
        this.name = name;
        this.callSign = callSign;
        this.missions = missions;
        setStatus();
    }



    public void setStatus()
    {
        if (missions >= 0 && missions <= 5)
        {
            status = "Rookie";
        }
        else if (missions >= 5 && missions <= 10)
        {
            status = "Trained";
        }
        else if (missions >= 10 && missions <= 16)
        {
            status = "Veteran";
        }
        else if (missions > 16)
        {
            status = "Ace pilot";
        }
        else
        {
            System.out.println("\nSystem Error!!, Missions cannot be negative\n");
        }
    }

    public String getName()
    {
        return name;
    }

    public String getCallSign()
    {
        return callSign;
    }

    public int getMissions()
    {
        return missions;
    }

    public String getStatus()
    {
        return status;
    }

    public void incMissions()
    {
        ++missions;
        setStatus();
    }

    public String toString() {
        String description = "\nAircrew" + "\n[ "
                + "\n\tName: " + name
                + "\n\tCall Sign: " + callSign
                + "\n\tMissions: " + missions
                + "\n\tStatus: " + status + "\n]";
        return description;
    }
}
