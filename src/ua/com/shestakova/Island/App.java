package ua.com.shestakova.Island;


import ua.com.shestakova.Island.settings.Parser;

public class App {

    public static void main(String[] args) {

       // Dialog.start();

        Parser parser = new Parser();
        parser.writerToJson();
    }
}


