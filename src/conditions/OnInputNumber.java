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

package conditions;

import sample.PuzzleView;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class OnInputNumber extends AbstractCondition {
    private int number;
    private PuzzleView puzzleView;

    public OnInputNumber(int number) {
        this.number = number;
    }

    public void setPuzzleView(PuzzleView puzzleView) {
        this.puzzleView = puzzleView;
    }

    @Override
    protected void onFirstSubscriber(Subscriber subscriber) {
        puzzleView.addOnNumberInputListener(number, (num) -> trigger());
    }
}
