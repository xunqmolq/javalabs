package com.example.demo.controller;

import com.example.demo.buisneslogic.Function;
import com.example.demo.dao.DaoFunction;
import com.example.demo.dao.IDAO;
import com.example.demo.dao.WritedFunction;
import org.springframework.web.bind.annotation.*;

import java.nio.file.NoSuchFileException;


// curl -X GET localhost:8080/3 -H 'Content-type:application/json' -d '{"lang": "ukr"}'
// curl -X POST localhost:8080/3 -H 'Content-type:application/json' -d '{"start": 0, "end": 1, "step":0.1}'
@RestController
public class MainController {

    IDAO idao;

    public MainController() {
        idao = new DaoFunction();
    }

    @PostMapping("/{id}")
    public String setTask(@RequestBody IntegralInfo integralInfo, @PathVariable("id") Integer id) throws NoSuchFileException {
//        Calculator.calculate(info, id);
        Function function = new Function();
        double result = function.countIntegral(integralInfo.getStart(), integralInfo.getEnd(), integralInfo.getStep());
        WritedFunction writedFunction = new WritedFunction(function.getName(), result, integralInfo.getStep(), integralInfo.getStart(), integralInfo.getEnd(), id);
        idao.setWritedFunction(writedFunction);
        return "result = " + result + "\n";
    }

    @GetMapping("/{id}")
    public String getTask(@RequestBody LangCheck langCheck, @PathVariable("id") Integer id) throws NoSuchFileException {
        LangUtil langUtil = new LangUtil(langCheck.lang);
        ReturningString returningString = new ReturningString(langUtil, idao.getWritedFunction(id));
        return returningString.getJsonReturn();
    }


}
class LangCheck{
    public String lang;
}

class ReturningString {
    LangUtil langUtil;
    private final WritedFunction writedFunction;

    public ReturningString(LangUtil langUtil, WritedFunction writedFunction) {
        this.langUtil = langUtil;
        this.writedFunction = writedFunction;
    }

    public String getJsonReturn() {
        return switch (langUtil.getLang()) {
            case "ukr" -> getUkrJsonReturn();
            case "eng" -> getEngJsonReturn();
            default -> throw new IllegalArgumentException("Illegal language");
        };
    }

    private String getUkrJsonReturn() {
        return "{\n\t\"Функція\": " + writedFunction.getFunction()
                + ",\n\t\"Алгоритм\": " + langUtil.getAlgoName()
                + ",\n\t\"Інтервал\": [" + writedFunction.getStart() + "; " + writedFunction.getEnd() + "]"
                + ",\n\t\"Крок\": " + writedFunction.getStep()
                + ",\n\t\"Результат\": " + writedFunction.getResult()
                + "\n}\n";
    }

    private String getEngJsonReturn() {
        return "{\n\t\"Function\": " + writedFunction.getFunction()
                + ",\n\t\"Algorithm\": " + langUtil.getAlgoName()
                + ",\n\t\"Interval\": [" + writedFunction.getStart() + "; " + writedFunction.getEnd() + "]"
                + ",\n\t\"Step\": " + writedFunction.getStep()
                + ",\n\t\"Result\": " + writedFunction.getResult()
                + "\n}\n";
    }
}