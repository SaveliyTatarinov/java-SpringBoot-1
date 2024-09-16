package ru.tatarinov.MyFirstAppSpringBoot.hello;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {
    private static List<String> arrayList = new ArrayList<>();
    private static Map<Integer, String> hashMap = new HashMap<>();

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/update-array")
    public ResponseEntity<String> updateArrayList(@RequestParam Map<String, String> s) {
        if (s == null || s.isEmpty()){
            return ResponseEntity.badRequest().body("Нет параметров");
        }
        if (s.size() != 1) {
            return ResponseEntity.badRequest().body("Слишком много параметров" +  " " + s.size());
        }
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        String key = s.keySet().iterator().next();
        arrayList.add(s.get(key));
        return ResponseEntity.ok("Element added to ArrayList: " + s);
    }

    @GetMapping("/show-array")
    public List<String> showArrayList() {
        return arrayList;
    }

    @GetMapping("/update-map")
    public ResponseEntity<String> updateHashMap(@RequestParam Map<String, String> s) {
        if (s == null || s.isEmpty()){
            return ResponseEntity.badRequest().body("Нет параметров");
        }
        if (s.size() != 1) {
            return ResponseEntity.badRequest().body("Слишком много параметров" +  " " + s.size());
        }
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        String key = s.keySet().iterator().next();
        hashMap.put(hashMap.size(), s.get(key));
        return ResponseEntity.ok("Element added to HashMap: " + s);
    }

    @GetMapping("/show-map")
    public Map<Integer, String> showHashMap() {
        return hashMap;
    }

    @GetMapping("/show-all-length")
    public String showAllLength() {
        return "ArrayList size: " + arrayList.size() + ", HashMap size: " + hashMap.size();
    }
}
