package edu.kit.informatik;

public class ExceptionChecker {

	/**
	 * Checks if the print command was given with the proper number of arguments
	 * 
	 * @param inputLength the number of arguments given with the print command
	 * @return true if the print command was given without arguments else false
	 */
	public boolean checkPrintWithoutArguments(int inputLength) {
		boolean valid = true;
		if (inputLength > 1) {
			valid = false;
			Terminal.printError("no arguments allowed with the print command");
		}
		return valid;
	}

	/**
	 * Checks if the place command was given with the proper number of arguments
	 * 
	 * @param inputLength the number of arguments given with the place command
	 * @return true if the place command was given with the proper number of 
	 * arguments else false
	 */
	public boolean checkPlaceProperNumberOfArguments(int inputLength) {
		boolean valid = true;
		if (inputLength > 4) {
			valid = false;
			Terminal.printError("wrong number of arguments");
		}
		return valid;
	}

	/**
	 * Checks if the state command was given with the proper number of arguments
	 * 
	 * @param inputLength the number of arguments given with the state command
	 * @return true if the state command was given with the proper number of 
	 * arguments else false
	 */
	public boolean checkStateProperNumberOfArguments(int inputLength) {
		boolean valid = true;
		if (inputLength > 2) {
			valid = false;
			Terminal.printError("unexisting slot");
		}

		return valid;
	}

	/**
	 * Checks if the quit command was given with the proper number of arguments
	 * 
	 * @param inputLength the number of arguments given with the quit command
	 * @return true if the quit command was given with the proper number of 
	 * arguments else false
	 */
	public boolean checkQuitWithoutArguments(int inputLength) {
		boolean valid = true;
		if (inputLength > 1) {
			valid = false;
			Terminal.printError("no arguments allowed with the quit command");
		}

		return valid;
	}

	/**
	 * Checks if the colprint command was given with the proper number of arguments
	 * 
	 * @param inputLength the number of arguments given with the colprint command
	 * @return true if the colprint command was given with the proper number of
	 * arguments else false
	 */
	public boolean checkPrintColProperArguments(int inputLength) {
		boolean valid = true;
		if (inputLength > 1) {
			valid = false;
			Terminal.printError("unexisting column");
		}

		return valid;
	}

	/**
	 * Checks if the rowprint command was given with the proper number of arguments
	 * 
	 * @param inputLength the number of arguments given with the rowprint command
	 * @return true if the rowprint command was given with the proper number of 
	 * arguments else false
	 */
	public boolean checkPrintRowProperArguments(int inputLength) {
		boolean valid = true;
		if (inputLength > 1) {
			valid = false;
			Terminal.printError("unexisting row");
		}

		return valid;
	}

}