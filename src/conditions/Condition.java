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

import java.util.function.Consumer;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public interface Condition {
    boolean isSatisfied();
    default Condition thenDo(Consumer<? super Condition> consumer) {
        subscribe(new Subscriber.Default() {
            @Override
            public void onSatisfied(Condition condition) {
                consumer.accept(condition);
            }
        });
        return this;
    }
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    default AndThen andThen(Condition condition) {
        return new AndThen(this, condition);
    }

    interface Subscriber {
        void onSatisfied(Condition condition);
        void onUnsatisfied(Condition condition);

        class Default implements Subscriber {
            @Override
            public void onSatisfied(Condition condition) { }

            @Override
            public void onUnsatisfied(Condition condition) { }
        }
    }
}
