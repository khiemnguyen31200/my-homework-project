package vn.techmaster.homework1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import vn.techmaster.homework1.model.Student;

@Controller
@RequestMapping("")
public class api {

    private static String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static String digits = "0123456789";
    private static String random = alpha + alphaUpperCase + digits;
    private static Random generator = new Random();
    

    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
    

    @GetMapping("/random")
    @ResponseBody
    public String random() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int number = randomNumber(0, random.length() - 1);
            char ch = random.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    @GetMapping("/qoute")
    @ResponseBody
    public String listQuote() {
        int count = randomNumber(1,5);
        switch(count) {
            case 1:
              return "Kiến tha lâu đầy tổ";
            case 2:
              return "Có công mài sắt, có ngày nên kim";
            case 3:
              return "Không thầy đố mày làm nên";
            case 4:
              return "Học thầy không tày học bạn";
            default:
              return "Kiến tha lâu đầy tổ";
          }
    }

    @PostMapping("/bmi")
    @ResponseBody
    public String bmiCal(@RequestParam int kg,@RequestParam int cm) {
        double chiSoBMI = kg / (cm*0.01 * cm*0.01);
        if (chiSoBMI < 18) {
            return "Chỉ số của bạn là "+chiSoBMI+" Bạn là người gầy!";
        } else if (chiSoBMI <= 24.9) {
            return "Chỉ số của bạn là "+chiSoBMI+" Bạn là người bình thường";
        } else if (chiSoBMI <= 29.9) {
            return "Chỉ số của bạn là "+chiSoBMI+" Bạn bị béo phì độ I";
        } else if (chiSoBMI <= 34.9) {
            return "Chỉ số của bạn là "+chiSoBMI+" Bạn bị béo phì độ II";
        } else {
            return "Chỉ số của bạn là "+chiSoBMI+" Bạn bị béo phì độ III";
        }
    }

    List<Student> students = new ArrayList<>();

    @GetMapping("/student")
    @ResponseBody
    public List<Student> students() {
        return students;
    }

    @PostMapping("/student")
    @ResponseBody
    public List<Student> addStudent(@RequestParam String id, @RequestParam String name,
            @RequestParam String classManager) {
        students.add(new Student(id, name, classManager));
        return students;
    }

}
