package com.rentalBikes.RentalBikes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RentalBikesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalBikesApplication.class, args);
	}

}

public class OutputPrinter {
	  public void welcome() {
	    printWithNewLine("Welcome to Bike Parking lot.");
	  }
	  public void end() {
	    printWithNewLine("Thanks for using Bike Parking lot service.");
	  }
	  public void notFound() {
	    printWithNewLine("Not found");
	  }

	  public void statusHeader() {
	    printWithNewLine("Slot No.\tRegistration No.\tColor");
	    printWithNewLine("Slot No.    Registration No    Colour");
	  }

	  public void parkingLotFull() {
	    printWithNewLine("Sorry, parking lot is full");
	  }
	  public void parkingLotEmpty() {
	    printWithNewLine("Parking lot is empty");
	  }
	  public void invalidFile() {
	    printWithNewLine("Invalid file given.");
	  }
	  public void printWithNewLine(final String msg) {
	    System.out.println(msg);
	  }
	}
	  15  
	src/main/java/com/uditagarwal/commands/StatusCommandExecutor.java
	@@ -42,9 +42,18 @@ public void execute(Command command) {
	    outputPrinter.statusHeader();
	    for (Slot slot : occupiedSlots) {
	      final Bike parkedBike = slot.getParkedBike();
	      outputPrinter.printWithNewLine(slot.getSlotNumber()
	          + "\t\t" + parkedBike.getRegistrationNumber()
	          + "\t\t" + parkedBike.getColor());
	      final String slotNumber = slot.getSlotNumber().toString();

	      outputPrinter.printWithNewLine(padString(slotNumber, 12)
	          + padString(parkedBike.getRegistrationNumber(), 19) + parkedBike.getColor());
	    }
	  }

	  private static String padString(final String word, final int length) {
	    String newWord = word;
	    for(int count = word.length(); count < length; count++) {
	      newWord = newWord + " ";
	    }
	    return newWord;
	  }
	}


	public class StatusCommandExecutor extends CommandExecutor {
		  public static String COMMAND_NAME = "status";
		  public StatusCommandExecutor(final ParkingLotService parkingLotService,
		      final OutputPrinter outputPrinter) {
		    super(parkingLotService, outputPrinter);
		  }
		  /**
		   * {@inheritDoc}
		   */
		  @Override
		  public boolean validate(final Command command) {
		    return command.getParams().isEmpty();
		  }
		  /**
		   * {@inheritDoc}
		   */
		  @Override
		  public void execute(Command command) {
		    final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
		    if (occupiedSlots.isEmpty()) {
		      outputPrinter.parkingLotEmpty();
		      return;
		    }
		    outputPrinter.statusHeader();
		    for (Slot slot : occupiedSlots) {
		      final Bike parkedBike = slot.getParkedBike();
		      outputPrinter.printWithNewLine(slot.getSlotNumber()
		          + "\t\t" + parkedBike.getRegistrationNumber()
		          + "\t\t" + parkedBike.getColor());
		      final String slotNumber = slot.getSlotNumber().toString();

		      outputPrinter.printWithNewLine(padString(slotNumber, 12)
		          + padString(parkedBike.getRegistrationNumber(), 19) + parkedBike.getColor());
		    }
		  }

		  private static String padString(final String word, final int length) {
		    String newWord = word;
		    for(int count = word.length(); count < length; count++) {
		      newWord = newWord + " ";
		    }
		    return newWord;
		  }
		}














