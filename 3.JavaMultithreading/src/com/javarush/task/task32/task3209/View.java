package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ArchMage on 07.04.17.
 */
public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public View(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            ExceptionHandler.log(e);
        } catch (InstantiationException e) {
            ExceptionHandler.log(e);
        } catch (IllegalAccessException e) {
            ExceptionHandler.log(e);
        } catch (UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
        }
    }

    public void init(){
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);
    }

    public void selectedTabChanged(){
        if (tabbedPane.getSelectedIndex() == 0) {
            controller.setPlainText(plainTextPane.getText());
        } else {
            plainTextPane.setText(controller.getPlainText());
        }
        resetUndo();
    }

    public void initMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        getContentPane().add(menuBar, BorderLayout.NORTH);

    }

    public void initEditor(){
        Dimension d = new Dimension(800, 500);
        htmlTextPane.setContentType("text/html");
        JScrollPane scrollHtml = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML" ,scrollHtml);
        plainTextPane.setContentType("text/html");
        JScrollPane scrollText = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", scrollText);
        tabbedPane.setPreferredSize(d);
        TabbedPaneChangeListener tabChangeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(tabChangeListener);
        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public void resetUndo(){
        undoManager.discardAllEdits();
    }

    public void exit(){
        controller.exit();
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public boolean canUndo(){
        return undoManager.canUndo();
    }

    public boolean canRedo(){
        return undoManager.canRedo();
    }

    public void undo(){
        try {
            undoManager.undo();
        } catch (Exception e){
            ExceptionHandler.log(e);
        }
    }

    public void redo(){
        try{
            undoManager.redo();
        } catch (Exception e){
            ExceptionHandler.log(e);
        }
    }

    public boolean isHtmlTabSelected(){
        if (tabbedPane.getSelectedIndex() == 0) { return true; }
        else {return false;}
    }

    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update(){
        HTMLDocument document = controller.getDocument();
        selectHtmlTab();
        htmlTextPane.setDocument(document);
    }

    public void showAbout(){
        JOptionPane.showMessageDialog(this, "Сообщение", "Текст сообщения", JOptionPane.INFORMATION_MESSAGE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if ("Новый".equals(action)) {
            controller.createNewDocument();
        }else if ("Открыть".equals(action)){
            controller.openDocument();
        }else if ("Сохранить".equals(action)){
            controller.saveDocument();
        }else if ("Сохранить как...".equals(action)){
            controller.saveDocumentAs();
        }else if ("Выход".equals(action)){
            controller.exit();
        }else if ("О программе".equals(action)){
            showAbout();
        }
    }
}
