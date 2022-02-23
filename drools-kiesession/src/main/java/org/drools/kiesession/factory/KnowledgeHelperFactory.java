/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.kiesession.factory;

import org.drools.core.common.ReteEvaluator;
import org.drools.core.spi.KnowledgeHelper;
import org.drools.kiesession.consequence.DefaultKnowledgeHelper;
import org.kie.api.internal.utils.KieService;

public interface KnowledgeHelperFactory extends KieService {

    KnowledgeHelper createKnowledgeHelper(ReteEvaluator reteEvaluator);

    class Holder {
        private static final KnowledgeHelperFactory INSTANCE = createInstance();

        static KnowledgeHelperFactory createInstance() {
            KnowledgeHelperFactory factory = KieService.load( KnowledgeHelperFactory.class );
            return factory != null ? factory : new KnowledgeHelperFactoryImpl();
        }
    }

    static KnowledgeHelperFactory get() {
        return KnowledgeHelperFactory.Holder.INSTANCE;
    }

    class KnowledgeHelperFactoryImpl implements KnowledgeHelperFactory {

        @Override
        public KnowledgeHelper createKnowledgeHelper(ReteEvaluator reteEvaluator) {
            return new DefaultKnowledgeHelper( reteEvaluator );
        }
    }
}