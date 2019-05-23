package com.emon.recylerview.lib;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Carrier
{
    private final int MAX_AIRCRAFT = 90;
    private int currentPlanes;
    private Aircraft [] planes;
    private int numPlane=0;

    private final int ADD_AIRCRAFT = 1;
    private final int DISPLAY = 2;
    private final int ADD_AIRCREW = 3;
    private final int LAUNCH = 4;
    private final int LANDING = 5;
    private final int SAVE_TO_FILE = 6;
    private final int EXIT = 7;

    private final int DISPLAY_ALL = 11;
    private final int DISPLAY_ALL_AIRCRAFT = 12;
    private final int DISPLAY_ALL_AIRCREW = 13;
    private final int DISPLAY_SINGLE_AIRCRAFT = 14;
    private final int DISPLAY_SINGLE_AIRCREW = 15;
    private final int DISPLAY_AIRCRAFT_ON_DECK = 16;
    private final int DISPLAY_AIRCRAFT_AIRBORNE = 17;
    private final int DISPLAY_EXIT = 18;

    private Scanner kb;

    public static void main( String [ ] args ) throws IOException
    {
         Carrier c = new Carrier( );
        // c.load( );
         c.run( );
    }

    public Carrier( )
    {
         kb = new Scanner( System.in );
         planes = new Aircraft[ MAX_AIRCRAFT ];
    }

    /*
     * This method asks the user for an input text file name.
     * The format of the file is detailed in the Assignment.
     * You may assume that the format is always correct.
     *
     * Open the text file and go the file, populating the
     * array of Aircraft with the information in the file
     * you have just opened.
     *
     * Then close the file.
     *
     * This method runs before the user sees the menu for the first 
     * time.
     *
     * If you have any trouble with this method, leave it blank
     * and get things working from the keyboard. You can always
     * come back to this method
     *
     */
    public void load( ) throws IOException
    {

        System.out.print("Enter file name>> ");
        String fileName = kb.nextLine();
        File f = new File(fileName);
        Scanner fileInput = new Scanner(f);

        if (!fileInput.hasNextLine()) {
            System.out.println("\nFile is empty");
        } else {
            while (fileInput.hasNextLine() && (planes.length!=0)) {
                String tailCode = fileInput.nextLine();
                String status = fileInput.nextLine();
                int numberOfCrew = fileInput.nextInt();
                int numberOfActualAirCrew=fileInput.nextInt();


                //Input nite problem hoile ei line ta comment out kore try kore dekhis
               // fileInput.nextLine();


                if (planes.length<=MAX_AIRCRAFT) {
                    Aircraft plane1 = new Aircraft(tailCode, status,numberOfCrew,numberOfActualAirCrew);

                    //Jodi actual pilot 0 hoy tahole ami input na niye next biman er data input nite chole jabo
                    if (numberOfActualAirCrew != 0) {

                        String name = fileInput.nextLine();
                        String callSign = fileInput.nextLine();
                        int missions = fileInput.nextInt();

                        //Input nite problem hoile ei line ta comment out kore try kore dekhis
                        // fileInput.nextLine();

                        plane1.addAircrew(name, callSign);//ekhane 1st e pilot banaiya input dilam plane e.


                        if(planes.length!=0)
                        planes[numberOfActualAirCrew]=plane1;//ekhane er por oi biman ta add kore dilam array te.
                        numberOfActualAirCrew++;

                    }
                    else {
                        planes[numberOfActualAirCrew]=plane1;//ei condition e kono pilot nai so ami direct biman add kore dilam;
                        numberOfActualAirCrew++;
                    }


                }

            }
        }
        fileInput.close();
    }

    public void run( ) throws IOException
    {
         int choice = -1;
         while( choice != EXIT )
         {
              displayMainMenu( );
              System.out.print( "Enter choice >> " );
              choice = kb.nextInt( );
              kb.nextLine( );
              processMain( choice );
         }
    }

    public void displayMainMenu( )
    {
         System.out.println( "\nOps Main Menu\n" );
         System.out.println( "\t" + ADD_AIRCRAFT + ". Add aircraft");
         System.out.println( "\t" + DISPLAY + ". Display Menu" ); 
         System.out.println( "\t" + ADD_AIRCREW + ". Add air crew" );
         System.out.println( "\t" + LAUNCH + ". Launch Aircraft" );
         System.out.println( "\t" + LANDING + ". Land Aircraft" );
         System.out.println( "\t" + SAVE_TO_FILE + ". Save to file");
         System.out.println( "\t" + EXIT + ". Shut down ops");
    }
    
    public void display(String msgString){
        
        System.out.println(msgString); 
    }

    public void processMain( int choice  ) throws IOException
    {
         switch( choice )
         {
              case ADD_AIRCRAFT :
                   addAircraft( );
                 break;

              case ADD_AIRCREW :
                   addAircrew( );
                 break;

              case DISPLAY :
                   display( );
                  break;

              case SAVE_TO_FILE :
                   save( );
                 break;

              case LAUNCH :
                   launch( );
                 break;

              case LANDING :
                   land( );
                 break;

              case EXIT :
                   // just trap this choice so that it doesn't show as
                   // an error
                 break;

              default:
                   System.out.println("That is not a valid choice " +
                                      "\nPlease re-enter");
                 break;
         }
     
    }

    /*
     * This method adds an Aircraft, using the keyboard, very much
     * like Assignment C, except that the address of the new Aircraft
     * is stored in the array, not a stand alone Aircraft object
     * reference.
     *
     * As in Assignment C, you must check that the tail code entered
     * by the user is not already in use.
     *
     */
    private void addAircraft( )
    {

            System.out.print("Enter tail code >> ");
            
            String tailCode = kb.nextLine();


                if (isAlreadyTailCodeAssigned(tailCode)) {
                    System.out.println("That tail code is already assigned\n" + "Tail codes must be unique");
                } else {

                    //Dst ekhane kotha ache amar..ami bujtesina sir kemne chaise..evabe age assignment C te ami aircraft
                    //add korse deklam bt ekn toh air craft er sathe onk kisu alada add korte hoye..so ogola sir re boila
                    //clear hois...ar sir jodi bole ekhane toi egula input niya "new Aircraft(tailCode)" ei constructor
                    //e oi jinish gula dia aircraft banai dis..
                    Aircraft plane1 = new Aircraft(tailCode);
                    planes[numPlane++]=plane1;
                    
                }
            
        }


    public boolean isAlreadyTailCodeAssigned(String tailCode) {

        
      
        if(planes.length!=0)
        {
            for(int i=0;i<planes.length;i++) {
           if(planes[i]!=null&&planes[i].getTailCode().equalsIgnoreCase(tailCode))
               return true;
          
       }
        }

       return false;
    }

    /*
     * This method adds an Aircrew to an Aircraft.
     * The user is first asked for the tail code of an Aircraft.
     * If that Aircraft is found in the array and that Aircraft
     * has not reached its maximum number of Aircrew, then the 
     * user is asked to enter the call sign of the new Aircrew.
     *
     * The code has to go through all the call signs of all the
     * Aircrew in all the Aircraft. If the user entered call sign is
     * not found, then you can ask for the new Aircrew name and 
     * add the Aircrew to the Aircraft. You already know the index
     * of the Aircraft because this is what you looked up at the 
     * start of this method to see if there was an Aircraft with
     * the user entered tail code
     *
     */
    private void addAircrew( )
    {
        System.out.print("Enter tail code >> ");
        String tailCode = kb.nextLine();


        int index=getAirCraftIndex(tailCode);
        if (planes.length != 0 && getAirCraftIndex(tailCode)!=-1) {

                System.out.println("\nEnter Aircrew call sign >> ");
                String callSign = kb.nextLine();

                if(planes[index].isAlreadyHaveSameCallSign(callSign))
                    return;

                System.out.print("\nEnter Aircrew name >> ");
                String name = kb.nextLine();
                planes[index].addAircrew(name, callSign);

                display(planes[index].toString());



        } else  System.out.println("\nThe user is not asked for any further information");


}



    public int getAirCraftIndex(String tailCode){
        for(int i=0;i<planes.length;i++){
            
            if(planes[i]!=null)
            if(planes[i].getTailCode().equalsIgnoreCase(tailCode)){
                return i;
            }
        }
        return -1;
    }

    /*
     * This now becomes the top of the Display menu
     *
     */

    private void display( )
    {
         int choice = -1;
         while( choice != DISPLAY_EXIT )
         {
              displayDisplayMenu( );
              System.out.print( "Enter choice >> " );
              choice = kb.nextInt() + 10;
              kb.nextLine( );
              processDisplay( choice );
         }

    }

    /*
     * This method writes the contents of the array back to a 
     * text file. The text file does not have to have the same
     * name as the input file.
     *
     * Remeber to use the format shown in the Assignment, pages 11 - 12
     * The output file must be able to be used as an input file the
     * next time that the program is run.
     *
     * Remember to close the file at the end of this method.
     * If everything seems to be working but there is nothing in
     * the output file, one reason is that you have not closed the
     * output file. Java only finishes writing all the information
     * into the file when you close the file
     *
     */
    private void save( ) throws IOException
    {

    }

    /*
     * This method launches Aircraft, that is, starts them on a 
     * mission.
     *
     * The user first enters the tail code of an Aircraft. If that
     * Aircraft is found in the array, then the method checks
     * if the Aircraft is already on a mission.
     * If the Aircraft exists and is not on a mission, then the 
     * methods checks that the Aircraft has at least one Aircrew.
     *
     * If all of the above conditions are true, then and only then
     * is the user asked to enter the required status of the mission.
     *
     * If the overall status level of all the Aircrew of that Aircraft
     * is greater than or equal to the required mission status level,
     * then the Aircraft is launched.
     *
     * This is explained in much more detail in the Assignment document
     *
     */
    private void launch( )
    {

        System.out.print("Enter tail code >> ");
        String tailCode = kb.nextLine();

        int index=getAirCraftIndex(tailCode);
        if (index!=-1) {
            if (planes.length!=0) {
                if (planes[index].getStatus().equalsIgnoreCase("on deck")) {
                    System.out.print("\nEnter required Aircrew status >> ");
                    String status = kb.nextLine();

                    if (planes[index].checkQuals(status)) {
                        planes[index].launched();
                    } else {
                        System.out.println("\nThe Aircrew of this aircraft do not has the required qualification");
                    }
                } else {
                    System.out.println("\nThis Aircraft is already airborned");
                }
            } else {
                System.out.println("\nThis Aircraft does not has an Aircrew, cannot launch");
            }
        }

    }

    /*
     * This method is called to end a mission. The user enters
     * the tail code of an Aircraft. If that Aircraft is found
     * in the array and that Aircraft is actaully on a mission,
     * then that Aircraft ends its mission.
     *
     * All this does is set the Aircraft status to "on deck"
     *
     */
    private void land( )
    {

        System.out.print("\nEnter tail code >> ");
        String tailCode = kb.nextLine();

        int index=getAirCraftIndex(tailCode);
        if (index!=-1) {
            if (planes[index].getStatus().equalsIgnoreCase("airborne"))
            {
                planes[index].recoveredAircraft();
            }
            else
            {
                System.out.println("\nThis Aircraft is already on the deck");
            }
        }


    }


    private void displayDisplayMenu( )
    {
         System.out.println("\nDisplay Menu" );
         System.out.println( "\t" + ( DISPLAY_ALL - 10 ) + ". Display All" );
         System.out.println( "\t" + ( DISPLAY_ALL_AIRCRAFT - 10 ) + 
                             ". Display Aircraft only - no aircrew" );
         System.out.println( "\t" + ( DISPLAY_ALL_AIRCREW - 10 ) +
                             ". Display Aircrew only - no Aircraft" );
         System.out.println( "\t" + ( DISPLAY_SINGLE_AIRCRAFT - 10 ) +
                             ". Display single aircraft " );
         System.out.println( "\t" + ( DISPLAY_SINGLE_AIRCREW - 10 ) +
                             ". Display single aircrew" );
         System.out.println( "\t" + ( DISPLAY_AIRCRAFT_ON_DECK - 10 ) +
                             ". Display aircraft on deck" );
         System.out.println( "\t" + ( DISPLAY_AIRCRAFT_AIRBORNE - 10 ) +
                             ". Display airborne aircraft" );
         System.out.println( "\t" + ( DISPLAY_EXIT - 10 ) +
                             ". Return to main menu" );

    }

    private void processDisplay( int choice )
    {
         switch( choice )
         {
              case DISPLAY_ALL :
                   displayAll( );
                 break;

              case DISPLAY_ALL_AIRCRAFT :
                   displayAircraftOnly( );
                 break;

              case DISPLAY_ALL_AIRCREW :
                   displayAircrewOnly( );
                 break;

              case DISPLAY_SINGLE_AIRCRAFT :
                   displaySingleAircraft( );
                 break;

              case DISPLAY_SINGLE_AIRCREW :
                   displaySingleAircrew( );
                 break;

              case DISPLAY_AIRCRAFT_ON_DECK :
                   displayAircraftOnDeck( );
                 break;

              case DISPLAY_AIRCRAFT_AIRBORNE :
                   displayAirborne( );
                 break;

              case DISPLAY_EXIT :
                   // just trap this choice, so that it doesn't appear
                   // as an error
                 break;

              default :
                   System.out.println("\nError, that is not a valid choice" +
                                      " re enter your choice" ); 
         }
         
    }

    /*
     * This methods displays to the screen all the information in 
     * the array, both Aircraft and the the Aircrew associated with
     * each Aircraft.
     *
     * If the Aircraft class has a properly written toString( )
     * method, then this method is small and easy to code.
     *
     * See Lecture / Workshop 10 for more information
     *
     */
    private void displayAll( )
    {

        for(int i=0;i<planes.length;i++){
            if(planes[i]!=null){
                display(planes[i].toString());
            }
        }

    }

    /*
     * This method display all the Aircraft in the array, without
     * their Aircrew information, which makes it different to 
     * dsplayAll.
     *
     * One way of doing this is write a separate method in the
     * Aircrew class that just displays the Aircraft details,
     * this will be different to the toString( ) method which
     * displays everything
     *
     */
    private void displayAircraftOnly()
    {

        for(int i=0;i<planes.length;i++){
            if(planes[i]!=null){
                display(planes[i].toStringOnlyAirCraft());
            }
        }

    }


   /*
    * This method displays information about each Aircrew, without
    * displaying the Aircraft, so, once again, the toString( )
    * method cannot be called.
    *
    * One way to do this is to return a privacy leak free copy
    * of the Aircres array of each Aircraft, one Aircraft at a 
    * time, and display that array.
    *
    * There are other ways of doing it.
    *
    */
    private void displayAircrewOnly()
    {

        for (int i=0;i<planes.length;i++){
            if(planes[i]!=null)
            display(planes[i].toStringOnlyAirCrew());
        }


    }

    /*
     * This method displays information about a single Aircraft,
     * including any Aircrew attached to this Aircraft.
     *
     * The user enters the tail code of an Aircraft and if the 
     * Aircraft with this tail code is found in the array, then you
     * already know the index, so just display the Aircraft at
     * this index.
     *
     * If you have written the seach method, then you pass the user
     * entered tail code to the search function. If the search
     * function returns an index that is not -1, then it is one line
     * of code to display the Aircraft.
     *
     */
    private void displaySingleAircraft( )
    {

        System.out.print("Enter tail code >> ");
        String tailCode = kb.nextLine();

        for(int i=0;i<planes.length;i++){
            if(planes[i]!=null){
                if(planes[i].getTailCode().equalsIgnoreCase(tailCode))display(planes[i].toString());
            }
        }


    }

    /*
     * This method displays the information about a single 
     * Aircrew, without the Aircraft that the Aircrew is part of.
     *
     * The user enters a call sign and the program displays the
     * Aircrew with this call sign, if they are found in the array
     * of Aircraft.
     *
     * Hint: This is a bit like adding an aircrew, you need to go 
     * through the Aircrew array of each Aircraft until you find
     * the Aircrew with that call sign, or search all the Aircrew
     * crews and not find the requested call sign
     *
     *
     */
    private void displaySingleAircrew()
    {


        display("Enter the call sing\n");
        String callSign = kb.nextLine();


        for(int i=0;i<planes.length;i++){
            if(planes[i]!=null){
boolean flag=false;
                for(int j=0;j<planes[i].getAircrews().length;j++)
                {
                    if(planes[i].getAircrews()[j]!=null){
                        if(planes[i].getAircrews()[j].getCallSign().equalsIgnoreCase(callSign)){

                            display(planes[i].getAircrews()[j].toString());
                            flag=true;

                        }

                    }
                }

                if(!flag)display("No Aircrew found");


            }
        }




    }
   
    /*
     * This method displays all the Aircraft, without Aircrew information,
     * that have status "on deck"
     *
     */
    private void displayAircraftOnDeck( )
    {

        for(int i=0;i<planes.length;i++){
            if(planes[i]!=null){
                if(planes[i].getNumberOfActualPilot()==0)
                    display("TailCode "+planes[i].getTailCode()+" is Ondeck");
            }
        }





    }
                

    /*
     * This method displays all the Aircraft, without Aircrew information,
     * that have status "airborne"
     *
     */
              
    private void displayAirborne( )
    {


        for(int i=0;i<planes.length;i++){
            if(planes[i]!=null){
                if(planes[i].getNumberOfActualPilot()!=0)
                    display("Tail Code "+planes[i].getTailCode()+" is AirBorne");
            }
        }


    }


}

