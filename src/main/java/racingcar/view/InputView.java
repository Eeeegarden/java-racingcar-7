package racingcar.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class InputView {
    // 자동차 이름 입력 메소드
    public static String inputCars(){
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분");
        String text = Console.readLine();
        return text;
    }

    public static String[] parseCars(String text) {
        text = text.replaceAll("^,+|,+$", ""); // 앞뒤 , 오는경우 제거
        String[] cars = text.split(",");
        if (text.isEmpty()) {
            throw new IllegalArgumentException("자동차는 최소 1대이상 입력해야합니다.");
        }
        cars = Arrays.stream(cars)
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .toArray(String[]::new);
        validateCars(cars);
        return cars;
    }


    // 시도 횟수 입력 메소드
    public static String inputAttempts() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String input = Console.readLine();
        return input;
    }

    public static int parseAttempts(String input) {
        int attempts = validateAttemptsInput(input);
        return attempts;
    }


    private static void validateCars(String[] cars) {
        Set<String> cheakUnique = new HashSet<>();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");

        for (String name : cars) {
            if (name.length() > 5) throw new IllegalArgumentException("자동차 이름은 5자 이하만 가능합니다.");
            if (!pattern.matcher(name).matches()) throw new IllegalArgumentException("자동차 이름은 영문자와 숫자만 포함해야 합니다.");
            if (!cheakUnique.add(name)) throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }

    private static int validateAttemptsInput(String input) {
        try {
            int attempts = Integer.parseInt(input);
            validateAttempts(attempts);
            return attempts;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도할 횟수는 숫자로 입력해야 합니다.");
        }
    }
     public static void validateAttempts(int attempts) {
        if (attempts <= 0) {
            throw new IllegalArgumentException("시도할 횟수는 양수를 입력해주세요");
        }
    }

}
