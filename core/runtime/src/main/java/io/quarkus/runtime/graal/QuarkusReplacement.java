/*
 * Copyright 2018 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.quarkus.runtime.graal;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

import io.quarkus.runtime.Quarkus;

/**
 * class that avoids the use of reflection in Quarkus when actually running
 * on Graal.
 * <p>
 * Graal does not seem to like registering the program entry point as availble for reflection
 */
@TargetClass(Quarkus.class)
final class QuarkusReplacement {

    @Substitute
    public static void main(String... args) throws Exception {
        GenMain.main(args);
    }

    @TargetClass(className = "io.quarkus.runner.GeneratedMain")
    static final class GenMain {

        @Alias
        public static void main(String... args) throws Exception {

        }

    }

}
