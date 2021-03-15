package com.codervibe;
/*
 * Created by Administrator on 2021/3/10  0010
 * DateTime:2021/03/10 10:40
 * Description:
 * Others:
 */

import com.sun.javafx.stage.WindowHelper;
import javafx.stage.FileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * @author Administrator
 * 文件切割和合并windows窗体
 * 方便操作
 */
public class FileCuttingMergeForm extends JFrame {
    public native void showMsgBox(String str);

    /**
     * 要加密的文件路径
     */
    String path = "";
    /**
     * 要加密的文件名
     **/
    String name = "";
    /**
     * 文件位置显示
     */
    JTextField FileShowJTextField = new JTextField();

    /**
     * 文件选择按钮
     */
    JButton FileChooesJButton = new JButton("...");

    /**
     * 文件位置显示
     */
    JTextField FileOutPutShowJTextField = new JTextField();

    /**
     * 文件输出选择按钮
     */
    JButton fileOutPutChooesJButton = new JButton("...");

    /**
     * 按文件大小分割按钮
     */
    JButton splitByEachFileSize = new JButton("按文件大小分割每个文件大小（1MB）");

    /**
     * 按输入的文件份数平均分割文件
     */
    JButton divideDocumentsEquallyAccordingToTheNumberOfInputDocuments = new JButton("按输入的文件份数平均分割文件");
    /**
     * 按照文件名合并文件
     */
    JButton combineFilesByFileName = new JButton("按照文件名合并文件，文件名格式：文件名+数字+.+扩展名");

    /**
     * 合并文件列表
     */
    JButton listOfMergedFiles = new JButton("合并文件列表");
    /**
     * 分割文件输入框
     */
    JLabel splitFileInputBox = new JLabel("分割文件输入框");
    /**
     * 合并文件输出框
     */
    JLabel combinedFileOutputBox = new JLabel("合并文件输出框");


    public FileCuttingMergeForm() {
        super();
        setVisible(true);
        setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height);
        setTitle("文件加密和解密工具");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

//-----------------------------------------------------------------------------------------------
        FileShowJTextField.setBounds(250, 89, 700, 30);
        FileShowJTextField.setFont(new Font("宋体", Font.BOLD, 20));
        add(FileShowJTextField);

        FileChooesJButton.setBounds(950, 89, 50, 30);
        add(FileChooesJButton);
//-----------------------------------------------------------------------------------------------
        FileOutPutShowJTextField.setBounds(250, 129, 700, 30);
        FileOutPutShowJTextField.setFont(new Font("宋体", Font.BOLD, 20));
        add(FileOutPutShowJTextField);

        fileOutPutChooesJButton.setBounds(950, 129, 50, 30);
        add(fileOutPutChooesJButton);
//-----------------------------------------------------------------------------------------------


        splitByEachFileSize.setBounds(250, 189, 300, 30);
        add(splitByEachFileSize);

        divideDocumentsEquallyAccordingToTheNumberOfInputDocuments.setBounds(600, 189, 300, 30);
        add(divideDocumentsEquallyAccordingToTheNumberOfInputDocuments);
        combineFilesByFileName.setBounds(250, 260, 450, 30);
        add(combineFilesByFileName);

        listOfMergedFiles.setBounds(700, 260, 300, 30);
        add(listOfMergedFiles);
//------------------------------------------------------------------------------------------------------

        splitFileInputBox.setBounds(150, 89, 120, 30);
        add(splitFileInputBox);

        combinedFileOutputBox.setBounds(150, 129, 120, 30);
        add(combinedFileOutputBox);


        /**
         * 文件选择按钮
         */
        FileChooesJButton.addActionListener(e -> {
            //这一部分就是把文件选择的框调出来
            JFileChooser chooser = new JFileChooser();//创建文件选择类
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "文本文档", "txt", "md", "mp3", "wav", "ppt");//文件选择的类型
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(chooser);

            if (returnVal == JFileChooser.APPROVE_OPTION) {

                //clooser.getSelectedFile().getName()为点击的文件名
                FileShowJTextField.setText(chooser.getSelectedFile().getPath());
                path = chooser.getSelectedFile().getPath();// 路径赋值
                name = chooser.getSelectedFile().getName();//文件名赋值
            }
        });
        fileOutPutChooesJButton.addActionListener(e -> {
            //这一部分就是把文件选择的框调出来
            JFileChooser chooser = new JFileChooser();//创建文件选择类
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "文本文档", "txt", "md", "mp3", "wav", "ppt");//文件选择的类型
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(chooser);

            if (returnVal == JFileChooser.APPROVE_OPTION) {

                //clooser.getSelectedFile().getName()为点击的文件名
                FileOutPutShowJTextField.setText(chooser.getSelectedFile().getPath());
                path = chooser.getSelectedFile().getPath();// 路径赋值
                name = chooser.getSelectedFile().getName();//文件名赋值
            }
        });


        /**
         * splitByEachFileSize
         * 按固定文件大小分割
         */
        splitByEachFileSize.addActionListener(e -> {
            System.out.println("FileShowJTextField.getText() = " + FileShowJTextField.getText());
            try {
                FileOperations.fileCut(FileShowJTextField.getText());
            } catch (IOException ioException) {
                ioException.printStackTrace();
                JOptionPane.showMessageDialog(null, "报错了"+ioException.getMessage() );

            }
            JOptionPane.showMessageDialog(null, "分割成功！");
        });
        /**
         * 根据输入要分割的数量平均地分割文档
         */
        divideDocumentsEquallyAccordingToTheNumberOfInputDocuments.addActionListener(e -> {
            int inputValue = Integer.parseInt(JOptionPane.showInputDialog("请输入要分割的数量:"));
            try {
                FileOperations.cutEvenlyByFileSize(FileShowJTextField.getText(), inputValue);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "报错了"+ioException.getMessage() );

                ioException.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "分割成功！" + inputValue);

        });
        /**
         * combineFilesByFileName
         * 按文件名合并文件
         */
        combineFilesByFileName.addActionListener(e -> {
            int inputValue = Integer.parseInt(JOptionPane.showInputDialog("请输入文件的数量"));
            JOptionPane.showMessageDialog(null, "请将文件名的格式改为指定格式，否则本方法无法执行");
            JOptionPane.showMessageDialog(null, "本方法多用于使用本软件切割的文件");
            System.out.println("FileShowJTextField.getText() = " + FileShowJTextField.getText());

            try {
                FileOperations.fileMerge(FileOutPutShowJTextField.getText(), inputValue);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                JOptionPane.showMessageDialog(null, "报错了"+ioException.getMessage() );
            }
            JOptionPane.showMessageDialog(null, "合并成功！");

        });
        /**
         * listOfMergedFiles
         * 合并文件列表
         */
        listOfMergedFiles.addActionListener(e -> {
            ArrayList<String> arrayList=new ArrayList();
            String fopsJT = FileOutPutShowJTextField.getText();
            String[] sourceStrArray = fopsJT.split(",");
            for (int i = 0; i < sourceStrArray.length; i++) {
                System.out.println(sourceStrArray[i]);
                arrayList.add(sourceStrArray[i]);

            }
            String filepath =JOptionPane.showInputDialog("Please input a value");
            try {
                FileOperations.specifyAListOfFilesToBeMerged(arrayList,filepath);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                JOptionPane.showMessageDialog(null, ioException.getMessage() );
            }
            JOptionPane.showMessageDialog(null, "合并成功！");



        });


    }

    /**
     * hereIsHowToSeparateTheFileNameAndPath
     * 这是如何分隔文件名和路径
     */
    public String getpath(String bigpath, String name) {
        String b = bigpath;
        //更换字符串B 中的 name 部分 为 空白
        b = b.replace(name, "");
        return b;
    }
}
