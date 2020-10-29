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

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class AndThen extends AbstractCondition {
    private Condition c1;
    private Condition c2;

    public AndThen(Condition c1, Condition c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    protected void onFirstSubscriber(Subscriber subscriber) {
        c1.subscribe(new Subscriber.Default() {
            @Override
            public void onSatisfied(Condition condition) {
                c2.subscribe(new Subscriber.Default() {
                    @Override
                    public void onSatisfied(Condition condition) {
                        setSatisfied(true);
                    }
                });
            }
        });
    }
}
