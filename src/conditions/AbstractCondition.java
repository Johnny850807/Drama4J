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

import java.util.HashSet;
import java.util.Set;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class AbstractCondition implements Condition {
    protected boolean satisfied;
    protected Set<Subscriber> subscribers = new HashSet<>();

    @Override
    public boolean isSatisfied() {
        return satisfied;
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
        if (subscribers.size() == 1) {
            onFirstSubscriber(subscriber);
        }
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    // 長期的條件： A靠近B
    // 觸發型條件：玩家使用道具X
    protected void trigger() {
        setSatisfied(true);
        setSatisfied(false); // 這是很重要的，使用道具要在正確的時機/條件，不是說曾經使用過就算Satisfied
    }

    protected void setSatisfied(boolean satisfied) {
        this.satisfied = satisfied;
        if (satisfied) {
            subscribers.forEach(s -> s.onSatisfied(this));
        } else {
            subscribers.forEach(s -> s.onUnsatisfied(this));
        }
    }

    protected abstract void onFirstSubscriber(Subscriber subscriber);
}
