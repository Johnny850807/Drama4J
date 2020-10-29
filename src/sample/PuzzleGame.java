/*
 * Copyright 2020 Waterball (johnny850807@gmail.com)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package sample;

import conditions.*;

import java.util.function.Consumer;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class PuzzleGame {
    private PuzzleView puzzleView;

    public void play() {
        puzzleView = new JFramePuzzleView();
        puzzleView.launch();

        // 三秒後，若使用者輸入3，就秀出
        Condition c1 = when(countdown(3000)) // 三秒後
                .andThen(inputNumber(3))  // 若使用者輸入3
                    .thenDo(showText("Hi, ... you can begin.")) // 就秀文字
                .andThen(inputNumber(3)) // 然後再輸入3
                    .thenDo(showText("Hi, ... your input is 3.")) // 再秀文字
                .andThen(countdown(4000)) // 然後等四秒
                    .thenDo(showText("What are you gonna do next...?")); // 再秀文字

        // 使用者'連續'輸入1 -> 2 -> 3 -> 4 的話就秀出文字
        Condition c2 = when(inputNumber(1))
                            .andThen(inputNumber(2))
                            .andThen(inputNumber(3))
                            .andThen(inputNumber(4))
                .thenDo(showText("HAHA!"));

        // 使用者'曾經'輸入過7, 8, 9，不管順序，就秀出文字
        Condition c3 = when(once(inputNumber(7)), once(inputNumber(8)), inputNumber(9))
                .thenDo(showText("7, 8, 9! HUH!?"));

        // 若c1, c2, c3都達成的話，兩秒後秀出文字，再一秒後秀出表情
        when(c1, c2, c3).andThen(countdown(2000))
                .thenDo(showText("Yes, you got it!"))
                .andThen(countdown(1000))
                .thenDo(showText("B-)"));

    }

    private Consumer<? super Condition> showText(String text) {
        return c -> puzzleView.setDescription(text);
    }

    private Conditions when(Condition... conditions) {
        return new Conditions(conditions);
    }

    private Once once(Condition condition) {
        return new Once(condition);
    }

    private OnCountdown countdown(long countdown) {
        return new OnCountdown(countdown);
    }

    private OnInputNumber inputNumber(int number) {
        OnInputNumber onInputNumber = new OnInputNumber(number);
        onInputNumber.setPuzzleView(puzzleView);
        return onInputNumber;
    }
}
