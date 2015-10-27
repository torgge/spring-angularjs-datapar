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



package com.jayway.restassured.internal.path.xml

import com.jayway.restassured.assertion.XMLAssertion
import com.jayway.restassured.path.xml.element.Node
import com.jayway.restassured.path.xml.element.NodeChildren

class NodeChildrenImpl extends NodeBase implements NodeChildren {
    def nodeList = []
    def groovyNodes

    Node get(int index) {
        return nodeList.get(index)
    }

    int size() {
        return nodeList.size()
    }

    boolean isEmpty() {
        return nodeList.isEmpty()
    }

    Iterator<String> iterator() {
        return new NodeListIterator()
    }

    def leftShift(Node node) {
        nodeList << node
    }

    public String toString() {
        def builder = new StringBuilder()
        nodeList.each {
            builder.append(it.toString())
        }
        builder.toString()
    }

    Iterable<Node> nodeIterable() {
        nodeList
    }

    public <T> T get(String name) {
        return get(name, nodeList.iterator(), false)
    }

    @Override
    def <T> T getPath(String path) {
        return new XMLAssertion(key: path).getChildResultAsJavaObject(groovyNodes) as T;
    }

    @Override
    Iterator<Node> nodeIterator() {
        return nodeList.iterator()
    }

    @Override
    List<Node> list() {
        return Collections.unmodifiableList(nodeList)
    }

    @Override
    def <T> List<T> getList(String name) {
        return get(name, nodeList.iterator(), true)
    }

    @Override
    Object getBackingGroovyObject() {
        return groovyNodes
    }

    class NodeListIterator implements Iterator<String> {
        def iterator = nodeList.iterator()

        @Override
        boolean hasNext() {
            return iterator.hasNext()
        }

        @Override
        String next() {
            def asString = iterator.next().toString()
            return asString
        }

        @Override
        void remove() {
            throw new UnsupportedOperationException()
        }
    }
}