package racingcar.view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.*;


class InputViewTest {

    @Test
    @DisplayName("차 이름 입력 테스트")
    public void inputCarsTest() {
        String input = "pobi,woni,jun";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String[] cars = InputView.inputCars();
        Assertions.assertThat(cars).containsExactly("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("자동차 이름 공백")
    public void emptyCarNameTest() {
        String input = "pobi,,jun";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThatThrownBy(InputView::inputCars)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 공백일 수 없습니다.");
    }

    @Test
    @DisplayName("자동차 이름 5자 초과")
    public void lengthCarNameTest() {
        String cars = "wooaahancorse";
        InputStream in = new ByteArrayInputStream(cars.getBytes());
        System.setIn(in);

        assertThatThrownBy(InputView::inputCars)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5자 이하만 가능합니다.");
    }

    @Test
    @DisplayName("자동차 이름 중복")
    public void duplicateCarNameTest() {
        String cars = "pobi,woni,pobi"; // 중복된 자동차 이름
        InputStream in = new ByteArrayInputStream(cars.getBytes());
        System.setIn(in);

        assertThatThrownBy(InputView::inputCars)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("시도 횟수 공백")
    public void emptyAttemptsTest() {
        String attempts = "";
        ByteArrayInputStream in = new ByteArrayInputStream(attempts.getBytes());
        System.setIn(in);

        assertThatThrownBy(InputView::inputAttempts)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시도할 횟수는 숫자로 입력해야 합니다.");
    }

    @Test
    @DisplayName("시도 횟수 숫자 X")
    public void InvalidAttemptsTest() {
        String attempts = "abc";
        ByteArrayInputStream in = new ByteArrayInputStream(attempts.getBytes());
        System.setIn(in);

        Assertions.assertThatThrownBy(InputView::inputAttempts)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시도할 횟수는 숫자로 입력해야 합니다.");
    }

    @Test
    @DisplayName("시도 횟수 양수 X")
    public void PositiveAttemptsTest() {
        String attempts = "-1";
        ByteArrayInputStream in = new ByteArrayInputStream(attempts.getBytes());
        System.setIn(in);

        Assertions.assertThatThrownBy(InputView::inputAttempts)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시도할 횟수는 양수를 입력해주세요");
    }
}