/*
 *     Copyright 2018 - 2019 Paul Hagedorn (Panzer1119)
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package de.codemakers.base.util.tough;

import de.codemakers.base.logger.Logger;

public interface Tough<T, R> {

    R action(T t) throws Exception;

    default R action(T t, ToughConsumer<Throwable> failure) {
        try {
            return action(t);
        } catch (Exception ex) {
            if (failure != null) {
                failure.acceptWithoutException(ex);
            } else {
                Logger.handleError(ex);
            }
            return null;
        }
    }

    default R actionWithoutException(T t) {
        return action(t, null);
    }

    boolean canConsume();

    boolean canSupply();

}
