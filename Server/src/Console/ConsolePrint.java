package Console;

public class ConsolePrint {
    public static void error_small(String message){
        System.out.println(ConsoleColors.RED_BRIGHT + message + ConsoleColors.RESET);
    }
    public static void error(String message){
        System.out.println(ConsoleColors.RED + message + ConsoleColors.RESET);
    }
    public static void error_big(String message){
        System.out.println(ConsoleColors.RED_BOLD + "ERROR: " + message + ConsoleColors.RESET);
    }
    public static void serverInfo(String message){
        System.out.println(ConsoleColors.YELLOW + message + ConsoleColors.RESET);
    }
    public static void gameInfo(String message){
        System.out.println(ConsoleColors.CYAN + message + ConsoleColors.RESET);
    }
    public static void success(String message){
        System.out.println(ConsoleColors.GREEN + message + ConsoleColors.RESET);
    }
    public static void new_message(String message, String sender){
        System.out.println(ConsoleColors.CYAN + "GET MESSAGE :: " + ConsoleColors.CYAN_BOLD + message + ConsoleColors.GREEN_BRIGHT + " from " + sender + ConsoleColors.RESET);
    }
    public static void message_to_player(String message, String receaver){
        System.out.println(ConsoleColors.PURPLE + "SEND MESSAGE :: " + ConsoleColors.PURPLE_BOLD + message + ConsoleColors.GREEN_BRIGHT + " to " + receaver + ConsoleColors.RESET);
    }
}
