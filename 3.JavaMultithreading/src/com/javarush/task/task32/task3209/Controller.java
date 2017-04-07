package com.javarush.task.task32.task3209;

import javax.swing.text.html.HTMLDocument;
import java.io.File;

/**
 * Created by ArchMage on 07.04.17.
 */
public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public void init(){

    }

    public void exit(){
        System.exit(0);
    }

    public Controller(View view) {
        this.view = view;
    }

    public static void main(String[] args){
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }
}
