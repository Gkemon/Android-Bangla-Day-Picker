package com.emon.recylerview.lib;

public class Aircraft {
    private Aircrew pilot;
    private String tailCode;
    private String status;

    public Aircrew[] getAircrews() {
        return aircrews;
    }

    public void setAircrews(Aircrew[] aircrews) {
        this.aircrews = aircrews;
    }

    private Aircrew [] aircrews;

    private int numberOfPilot=10;
    private int numberOfActualPilot=0;


    public int getNumberOfPilot() {
        return numberOfPilot;
    }

    public void setNumberOfPilot(int numberOfPilot) {
        this.numberOfPilot = numberOfPilot;
    }

    public int getNumberOfActualPilot() {
        return numberOfActualPilot;
    }

    public void setNumberOfActualPilot(int numberOfActualPilot) {
        this.numberOfActualPilot = numberOfActualPilot;
    }


    public Aircraft(String tailCode) {

        pilot = null;
        this.tailCode = tailCode;
        status = "On deck";
        this.aircrews = new Aircrew[numberOfPilot];

    }

    public Aircraft(String tailCode, String status,int numberOfPilot,int numberOfActualPilot) {
        pilot = null;
        this.tailCode = tailCode;
        this.status = status;
        this.numberOfPilot=numberOfPilot;
        this.numberOfActualPilot=numberOfActualPilot;
        this.aircrews = new Aircrew[numberOfPilot];


    }



    public String getTailCode() {
        return tailCode;
    }

    public String getStatus() {
        return status;
    }


    public String getCallSign() {
        if (pilot != null) {
            return pilot.getCallSign();
        }
        return ""; //empty
    }



    public void addAircrew(String name, String callSign) {
        pilot = new Aircrew(name, callSign);

       if(isAlreadyHaveSameCallSign(tailCode))return;

       for(int i=0;i< aircrews.length;i++){
           if(aircrews[i]==null){
               aircrews[i]=pilot;
               numberOfActualPilot++;
               return;
           }
       }


    }

    public boolean isAlreadyHaveSameCallSign(String callSign){
        for(int i=0;i< aircrews.length;i++){
            if(aircrews[i]!=null)if(aircrews[i].getCallSign().equalsIgnoreCase(callSign))
            {
                System.out.println("All ready have an pilot with this call sign");
                return true;
            }
        }
        return false;
    }

   /* public void addAircrew(String name, String callSign, int missions, String status) {
        pilot = new Aircrew(name, callSign, missions, status);
    }
*/

    public String toStringOnlyAirCrew() {
        StringBuilder description = new StringBuilder();

        if (aircrews.length !=0) {

            for(int i=0;i<aircrews.length;i++){

                if(aircrews[i]!=null)
                description.append(" ").append(aircrews[i].toString());
            }
        }
        else
        {
            description.append("\n this aircraft has no assogned any aircrew");
        }
        description.append("\n]\n");

        return description.toString();
    }


    public String toStringOnlyAirCraft() {
        StringBuilder description = new StringBuilder("Aircraft" + "\n[ "
                + "\n\tTail code: " + tailCode
                + "\n\tStatus: " + status);

        if (aircrews.length !=0) {

            for(int i=0;i<aircrews.length;i++){
                description.append(" ");
            }
        }
        else
        {
            description.append("\n this aircraft has no assogned any aircrew");
        }
        description.append("\n]\n");

        return description.toString();
    }

    public String toString() {
        String description = "Aircraft" + "\n[ "
                + "\n\tTail code: " + tailCode
                + "\n\tStatus: " + status;

        if (aircrews.length !=0) {

            for(int i=0;i<aircrews.length;i++){


                String msg = aircrews[i]==null?"":aircrews[i].toString();

                description += " " +msg;
            }
        }
        else
        {
            description += "\n this aircraft has no assogned any aircrew";
        }
        description += "\n]\n";

        return description;
    }

    public boolean checkQuals (String aircrewStatus)
    {
        boolean doCheck = false;

        if (aircrewStatus.equalsIgnoreCase("Rookie"))
        {
            doCheck = true;
        }
        else if (aircrewStatus.equalsIgnoreCase("Trained") )
        {
            if (pilot.getStatus().equalsIgnoreCase("Rookie"))
            {
                doCheck = true;
            }
        }
        else if (aircrewStatus.equalsIgnoreCase("Veteran"))
        {
            if (pilot.getStatus().equalsIgnoreCase("Veteran") || pilot.getStatus().equalsIgnoreCase("Ace pilot"))
            {
                doCheck = true;
            }
        }
        else if (aircrewStatus.equalsIgnoreCase("Ace pilot"))
        {
            if (pilot.getStatus().equalsIgnoreCase("Ace pilot"))
            {
                doCheck = true;
            }
        }
        else
        {
            System.out.println("\nSystem erroe!! Invalid mission requiremenr level\n");
        }

        return doCheck;
    }

    public void launched()
    {
        status = "airborne";
        pilot.incMissions();
    }

    public void recoveredAircraft()
    {
        status = "on deck";
    }
}
