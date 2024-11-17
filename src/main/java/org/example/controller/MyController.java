package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.example.compiler.FileWriteUtil.writeToFile;
import org.example.compiler.Compiler;

@Controller
public class MyController {
    private static final Compiler compiler = new Compiler();

    @GetMapping("/")
    public String index() {
        return "index"; // 返回Thymeleaf模板的名称
    }

    @PostMapping("/sendData")
    public String sendData(@RequestParam("inputData") String inputData, Model model) {
        // 这里可以添加处理数据的逻辑，比如调用服务层处理数据
        String responseData = processData(inputData); // 假设这是处理数据的方法

        // 将处理后的数据添加到Model中，以便在页面上显示
        model.addAttribute("responseData", responseData);
        return "index"; // 重新加载index页面，显示处理后的数据
    }

    private String processData(String data) {
        writeToFile("test03.cpp", data);
        compiler.PowerShellExecuteCommand("g++ test03.cpp");
        String result = compiler.PowerShellExecuteCommand("./a.exe");
        compiler.deleteFile("test03.cpp");
        compiler.deleteFile("a.exe");
        // 简单示例，实际应用中这里可能是更复杂的逻辑
        return "Compile Success! \n" + result;//git test
    }
}

