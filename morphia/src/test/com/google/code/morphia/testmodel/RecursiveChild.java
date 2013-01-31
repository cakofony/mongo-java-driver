/*
 * Copyright (c) 2008 - 2013 10gen, Inc. <http://10gen.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.code.morphia.testmodel;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.testutil.TestEntity;

@Entity("stuff")
public class RecursiveChild extends TestEntity {
    private static final long serialVersionUID = 1L;

    @Reference
    private RecursiveParent parent;

    public RecursiveChild() {
        super();
    }

    public RecursiveParent getParent() {
        return parent;
    }

    public void setParent(final RecursiveParent parent) {
        this.parent = parent;
    }
}
