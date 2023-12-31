package agendaStarter;

import exception.InformationRequiredException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import domein.*;
import java.util.*;

public class TestDrive {

    private final static Contact contactData[] = {
        new Contact("Van Schoor", "Johan", "Lector", "Hogeschool Gent"),
        new Contact("Samyn", "Stefaan", "Lector", "Hogeschool Gent"),
        new Contact("Malfait", "Irina", "Lector", "Hogeschool Gent"),
        new Contact("De Donder", "Margot", "Lector", "Hogeschool Gent"),
        new Contact("Decorte", "Johan", "Lector", "Hogeschool Gent"),
        new Contact("Samyn", "Karine", "Lector", "Hogeschool Gent")
    };
    private Appointment appt;
    //TODO attribut(en) voor aanmaak van een appointment
    //
   private Scheduler scheduler = new Scheduler();
   
    
    //
    //End TODO attribut(en)
    public static void main(String[] args) {
        new TestDrive().run();
    }
    
    private List<Contact> createAttendees(int numberToCreate) {
        List<Contact> group = new ArrayList<>();
        for (int i = 0; i < numberToCreate; i++) {
            group.add(getContact(i));
        }
        return group;
    }

    private Contact getContact(int index) {
        return contactData[index % contactData.length];
    }
    
    private void run() {

        System.out.println("Creating an Appointment ");
        //TODO maak gewone afspraak zonder fout:
        //
//        Start datum = LocalDateTime.of(2022, 7, 22, 12, 30)
//        Locatie = new Location("Hogeschool Gent, D2.014")
//        Beschrijving = "Project Demo"
//        uitgenodigden = createAttendees(4)
        try {
        	appt = scheduler.buildApointment(new AppointmentBuilder(),LocalDateTime.of(2023, 7, 22, 12, 30) , null, "Project Demo", createAttendees(4), new Location("Hogeschool Gent, D2.014"));
        	
                   
            
        //Afdruk resultaat
        
            System.out.println("Successfully created an Appointment.");
            System.out.println("Appointment information:");
            System.out.println(appt);
            System.out.println();
        //vervolg...(als fouten)
        }catch(InformationRequiredException ire) {
        	printExceptions(ire);
        }
       
   

        System.out.println("Creating a meeting : enddate is missing");
        //TODO maak een meeting met fout:
        //
        //Start datum = LocalDateTime.of(2022, 3, 21, 12, 30)
        //Locatie = new Location("Hogeschool Gent, B3.020")
        //Beschrijving = "OOO III"
        //uitgenodigden = createAttendees(4)
        
        
          try {
        	  appt = scheduler.buildApointment(new MeetingBuilder(), LocalDateTime.of(2023, 3, 21, 12, 30) , null, "OOO III", createAttendees(4), new Location("Hogeschool Gent, B3.020"));
        	  
         
                    
            
       //Afdruk resultaat (zal falen)
            System.out.println("Successfully created an Appointment.");
            System.out.println("Appointment information:");
            System.out.println(appt);
            System.out.println();
       //vervolg... (als fouten)
          }catch(InformationRequiredException ire) {
        	  printExceptions(ire);
          }
       

         
        System.out.println("Meeting : all items are provided\n");
        //TODO maak een meeting met fout:
        //
        //Start datum =LocalDateTime.of(2022, 4, 1, 10, 00)
        //Eind datum = LocalDateTime.of(2022, 4, 1, 11, 30),
        //Locatie = new Location("Hogeschool Gent, B1.032")
        //Beschrijving = "Project II Meeting"
        //uitgenodigden = createAttendees(2)

        
           try {
        	   appt = scheduler.buildApointment(new MeetingBuilder(), LocalDateTime.of(2023, 4, 1, 10, 00), LocalDateTime.of(2023, 4, 1, 11, 30), "Project II Meeting", createAttendees(2), new Location("Hogeschool Gent, B1.032"));
        	   
       
                
                    
            
        //Afdruk resultaat
            System.out.println("Successfully created an Appointment.");
            System.out.println("Appointment information:");
            System.out.println(appt);
            System.out.println();
        //vervolg...(als fouten)
           }catch(InformationRequiredException ire) {
        	   printExceptions(ire);
           }
    
        
        //
    }

    //TODO een printmethode voor bij fouten : wat er voor de constructie ontbreekt
    //
    //public void print...
    public void printExceptions(InformationRequiredException ire) {
    	System.out.println(ire.getMessage());
    	ire.getInformationRequired().forEach(e->System.out.println(e));
    }

        
        
            
            
        
    
    //
    //END TODO printmethode wat ontbreekt




}