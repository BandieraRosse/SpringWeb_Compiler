package org.example.compiler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Compiler {
    public void createFile(String fileName) {
        PowerShellExecuteCommand("New-Item -Path.\\"+fileName+" -ItemType File");
    }

    public void deleteFile(String fileName) {
        PowerShellExecuteCommand("del "+fileName);
    }

    public void writeFile(String fileName, String content) {
        PowerShellExecuteCommand("Add-Content -Path.\\"+fileName+" -Value \""+content+"\"");
        System.out.println("写入"+content);
    }

    public void writeMultipleLineFile(String fileName, String multiLineContent) { //无法处理()符号
        String[] parts = multiLineContent.split("\n");
        for (String part : parts) {
            writeFile(fileName, part);
        }
    }
    public String PowerShellExecuteCommand(String command) {
        try {
            // 创建一个List来存储要传递给ProcessBuilder的命令和参数
            List<String> commandList = new ArrayList<>();
            // 指定要启动PowerShell
            commandList.add("powershell.exe");
            // 添加要在PowerShell中执行的具体命令
            commandList.add(command);

            // 创建ProcessBuilder对象，传入命令列表
            ProcessBuilder processBuilder = new ProcessBuilder(commandList);
            // 启动进程
            Process process = processBuilder.start();

            // 读取命令的输出
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), "GBK")
            );

            String line;
            String result="";
            while ((line = reader.readLine())!= null) {
                System.out.println(line);
                result +=line;
                result +="\n";
            }
            // 等待命令执行结束
            int exitCode = process.waitFor();
            System.out.println("Exited with code : " + exitCode);
            return result;
        } catch (java.io.IOException | InterruptedException e) {
            e.printStackTrace();
            return "IOException";
        }
    }

    public void CmdexecuteCommand(String command){//由cmd修改而来
        try {
            // 创建ProcessBuilder对象，传入命令和参数
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", command);


            // 启动进程
            Process process = processBuilder.start();

            // 读取命令的输出
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待命令执行结束
            int exitCode = process.waitFor();
            System.out.println("Exited with code : " + exitCode);
        } catch (java.io.IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
