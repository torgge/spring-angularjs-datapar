/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jayway.restassured.internal.support

import com.jayway.restassured.config.ParamConfig.UpdateStrategy
import com.jayway.restassured.internal.NoParameterValue

import static com.jayway.restassured.config.ParamConfig.UpdateStrategy.MERGE
import static com.jayway.restassured.internal.assertion.AssertParameter.notNull
import static java.util.Arrays.asList

class ParameterUpdater {
  private final Serializer serializer;

  ParameterUpdater(Serializer serializer) {
    this.serializer = serializer
  }

  public void updateParameters(UpdateStrategy strategy, Map<String, Object> from, Map<String, Object> to) {
    notNull from, "Map to copy from"
    notNull to, "Map to copy to"
    notNull strategy, UpdateStrategy.class
    from.each { key, value ->
      updateStandardParameter(strategy, to, key, value)
    }
  }

  public void updateCollectionParameter(UpdateStrategy strategy, Map<String, Object> to, String key, Collection<Object> values) {
    if (values == null || values.isEmpty()) {
      to.put(key, new NoParameterValue())
      return;
    }

    def convertedValues = values.collect { serializer.serializeIfNeeded(it) }
    if (strategy == MERGE) {
      if (to.containsKey(key)) {
        def currentValue = to.get(key)
        if (currentValue instanceof Collection) {
          currentValue.addAll(convertedValues)
        } else {
          to.put(key, [currentValue, convertedValues].flatten())
        }
      } else {
        to.put(key, new LinkedList<Object>(convertedValues))
      }
    } else {
      to.put(key, convertedValues)
    }
  }

  public void updateZeroToManyParameters(UpdateStrategy strategy, Map<String, Object> to, String parameterName, Object... parameterValues) {
    if (isEmpty(parameterValues)) {
      updateStandardParameter(strategy, to, parameterName)
    } else if (parameterValues.length == 1) {
      updateStandardParameter(strategy, to, parameterName, parameterValues[0])
    } else {
      updateCollectionParameter(strategy, to, parameterName, asList(parameterValues))
    }
  }

  public void updateStandardParameter(UpdateStrategy strategy, Map<String, Object> to, String key) {
    updateStandardParameter(strategy, to, key, null)
  }

  public void updateStandardParameter(UpdateStrategy strategy, Map<String, Object> to, String key, Object value) {
    if (value == null) {
      to.put(key, new NoParameterValue())
      return;
    }
    def newValue = serializer.serializeIfNeeded(value)
    if (strategy == MERGE) {
      if (to.containsKey(key)) {
        def currentValue = to.get(key)
        if (currentValue instanceof List) {
          currentValue << newValue
        } else {
          to.put(key, [currentValue, newValue])
        }
      } else {
        to.put(key, newValue)
      }
    } else {
      to.put(key, newValue)
    }
  }

  private static boolean isEmpty(Object[] objects) {
    return objects == null || objects.length == 0 || (objects.length == 1 && objects[0] instanceof NoParameterValue)
  }

  public static interface Serializer {
    String serializeIfNeeded(Object value);
  }
}
